import { CompatClient, Frame, Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { BodyStore, populateEmptyCell } from '../components/gameloop/stores/BodyStore';
import { CreditStore } from '../components/gameloop/stores/CreditStore';

import { gotoDefaultGame, showErrDefaultGame } from '../components/selectModes/SelectModes';
import {
  gotoCustomGame,
  showErrCustomGame
} from '../components/setupGame/editConfig/GameSetupPage';

import { EventTypes } from './EventTypes';
import { handleGameOutput } from './GameController';

export type GameSetup = {
  antiGeneticCodes: { A1?: string; A2?: string; A3?: string };
  virusGeneticCodes: { V1?: string; V2?: string; V3?: string };
  gameConfig: string;
};

type GameStartResp = {
  status: string;
  msg: string;
  config: any;
};

export const emptyGameSetup: GameSetup = {
  gameConfig: '',
  antiGeneticCodes: {},
  virusGeneticCodes: {}
};

let client: CompatClient;
let isDefaultGame = false;

export let sessionId = '';

const url = 'http://localhost:8080/carin-websocket';

export const startDefaultGame = async (): Promise<void> => {
  isDefaultGame = true;
  console.log('start default game...');
  if (!client) {
    await connect();
  } else if (!client.connected) {
    await connect();
  }

  client.publish({
    destination: '/app/start',
    body: JSON.stringify(emptyGameSetup)
  });
};

export const startCustomGame = async (gameSetup: GameSetup): Promise<void> => {
  isDefaultGame = false;
  console.log('start custom game...');
  if (!client) {
    await connect();
  } else if (!client.connected) {
    await connect();
  }

  client.publish({
    destination: '/app/start',
    body: JSON.stringify(gameSetup)
  });
};

const connect = async (): Promise<void> => {
  return new Promise((resolve, reject) => {
    // cast to any to be able to access transport url
    // because transport is not defined in the type WebSocket (SockJS type)
    const socket = new SockJS(url) as any;

    client = Stomp.over(socket);
    client.connect({}, (frame: Frame) => {
      const transportUrl = socket._transport.url as string;

      // from https://stackoverflow.com/a/43430736/11968036
      sessionId = /\/([^/]+)\/websocket\/?$/.exec(transportUrl)![1];

      console.log('connected, session id: ' + sessionId);
      resolve();

      client.subscribe('/queue/game-' + sessionId, (event: Frame) => {
        console.log(JSON.parse(event.body));
        handleGameOutput(JSON.parse(event.body));
      });

      client.subscribe('/queue/start-' + sessionId, (resp: Frame) => {
        handleGameStart(JSON.parse(resp.body));
      });
    });
  });
};

export const sendMove = (move: EventTypes.InputMoveEvent): void => {
  console.log(move);
  if (client) {
    if (client.connected) {
      client.publish({
        destination: '/app/move/' + sessionId,
        body: JSON.stringify(move)
      });
    }
  }
};

export const sendBuy = (buy: EventTypes.BuyEvent): void => {
  console.log(buy);
  if (client) {
    if (client.connected) {
      client.publish({
        destination: '/app/buy/' + sessionId,
        body: JSON.stringify(buy)
      });
    }
  }
};

export const sendRestart = () : void => {
  console.log("restart...");
  if (client) {
    if (client.connected) {
      client.publish({
        destination: '/app/restart',
      });
    }
  }
}

// function that runs when game sucessfully starts
const handleGameStart = (resp: GameStartResp): void => {
  const status = resp.status;
  console.log(resp);

  if (status === 'SUCCESS') {
    // start game
    console.log('Successfully start game');

    BodyStore.update((s) => {
      s.m = resp.config.m;
      s.n = resp.config.n;
      s.Cell = populateEmptyCell(resp.config.m, resp.config.n);
    });

    CreditStore.update((s) => {
      s.creditData.initialCredit = resp.config.initialAntibodyCredits;
      s.creditData.credit = resp.config.initialAntibodyCredits;
      s.creditData.buyCost = resp.config.antibodyPlacementCost;
      s.creditData.moveHpCost = resp.config.antibodyMoveHpCost;
    })

    if (isDefaultGame) gotoDefaultGame();
    else gotoCustomGame();
  } else if (status === 'FAIL') {
    // retry
    console.log('Fail to start game: ' + resp.msg);
    if (isDefaultGame) showErrDefaultGame(resp.msg);
    else showErrCustomGame(resp.msg);
  }
};
