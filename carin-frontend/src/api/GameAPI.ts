import { CompatClient, Frame, Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { gotoGame, showError } from '../components/selectModes/SelectModes';

import { EventTypes } from './EventTypes';
import { handleGameOutput } from './GameController';

export type GameSetup = {
  antiGeneticCodes: {};
  virusGeneticCodes: {};
  gameConfig: string;
};

type GameStartResp = {
  status: string;
  msg: string;
  config: string;
};

let client: CompatClient;
export let sessionId = '';

const url = 'http://localhost:8080/carin-websocket';

export const startDefaultGame = async (): Promise<void> => {
  console.log('start default game...');
  if (!client) {
    await connect();
  } else if (!client.connected) {
    await connect();
  }

  const defaultGameSetup: GameSetup = {
    gameConfig: '',
    antiGeneticCodes: {},
    virusGeneticCodes: {}
  };

  client.publish({
    destination: '/app/start',
    body: JSON.stringify(defaultGameSetup)
  });
};

export const startCustomGame = async (gameSetup: GameSetup): Promise<void> => {
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

// function that runs when game sucessfully starts
const handleGameStart = (resp: GameStartResp): void => {
  const status = resp.status;
  console.log(resp);

  if (status === 'SUCCESS') {
    // start game
    console.log('Successfully start game');
    gotoGame();
  } else if (status === 'FAIL') {
    // retry
    console.log('Fail to start game: ' + resp.msg);
    showError(resp.msg);
  }
};
