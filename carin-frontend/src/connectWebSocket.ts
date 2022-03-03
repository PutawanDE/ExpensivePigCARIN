import { CompatClient, Frame, Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

export let stompClient : CompatClient;
export let sessionId = '';

const connect = (): void => {
  const socket = new SockJS('http://localhost:8080/carin-websocket', [], {
    sessionId: () => {
      return sessionId
   }
  });
  stompClient = Stomp.over(socket);
  stompClient.connect({}, (frame: Frame) => {
    console.log('connected, session id: ' + sessionId);
    stompClient.subscribe('/queue/game-' + sessionId, async (config: Frame) => {
      alert(JSON.parse(config.body).content);
    });
  });
};

export default connect;
