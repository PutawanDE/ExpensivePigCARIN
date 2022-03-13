import { CompatClient, Frame, Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

export type GameSetup = {
  antiGeneticCodes: string[];
  virusGeneticCodes: string[];
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

export const startGame = async (gameSetup: GameSetup): Promise<void> => {
  console.log('start game...');
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
      sessionId = /\/([^\/]+)\/websocket\/?$/.exec(transportUrl)![1];

      console.log('connected, session id: ' + sessionId);
      resolve();

      client.subscribe('/queue/game-' + sessionId, (frame: Frame) => {
        // game output event
      });

      client.subscribe('/queue/start-' + sessionId, (resp: Frame) => {
        handleGameStart(JSON.parse(resp.body));
      });
    });
  });
};

// function that runs when game sucessfully starts
const handleGameStart = (resp: GameStartResp): void => {
  console.log('sadasd');

  const status = resp.status;

  if (status === 'SUCCESS') {
    // start game
    console.log('Start game with config: ' + resp.config);
  } else if (status === 'FAIL') {
    // retry
    console.log('Fail to start game: ' + resp.msg);
  }
};
