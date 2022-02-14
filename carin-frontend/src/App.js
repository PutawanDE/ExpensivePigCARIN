import React from 'react';
import { stompClient } from './connectWebSocket.ts';

import logo from './logo.svg';
import './App.css';

function App() {
  const startGame = () => {
    stompClient.send('/app/start', {}, JSON.stringify({ name: 's' }));
  };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          target="_blank"
          rel="noopener noreferrer"
          onClick={(e) => startGame()}>
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
