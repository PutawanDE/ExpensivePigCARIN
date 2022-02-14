import { Frame, Stomp } from '@stomp/stompjs';

export let stompClient = null;
export let sessionId = '';

const connect = (): void => {
  const socket = new SockJS('http://localhost:8080/carin-websocket');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, (frame: Frame) => {
    sessionId = /\/([^/]+)\/websocket\/?$/.exec(socket._transport.url)[1];
    console.log('connected, session id: ' + sessionId);
    stompClient.subscribe('/queue/game-' + sessionId, async (config: Frame) => {
      alert(JSON.parse(config.body).content);
    });
  });
};

export default connect;
