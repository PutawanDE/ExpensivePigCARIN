import React from 'react';
import { sendRestart } from '../../../api/GameAPI';
import { EventTypes } from '../../../api/EventTypes';

import Trophy from '../../../elements/result/trophy.gif';
import V1 from '../assets/V1.gif';
import Restart from '../assets/restart.png';
import Exit from '../assets/exit.png';

import './GameEnd.css';

const GameEnd = (props: EventTypes.GameEndEvent) => {
  const restartGame = () => {
    sendRestart();
  };

  const exitGame = () => {
    window.location.replace('/');
  };

  return (
    <div className="popup-end">
      <div className="endbox">
        <div className="center text-2xl font-bold py-8 text-white">
          <div className="endcenter">
            <div className="text-4xl">GAME OVER</div>
            <div>
              {props.status === 'WIN' ? (
                <img src={Trophy} className="iconEnd" />
              ) : (
                <img src={V1} className="iconEnd" />
              )}
            </div>
            <div>YOU {props.status}</div>
            <div>{props.virusDeadCount} VIRUS DEADS </div>
            <div>{props.antiDeadCount} ANTIBODY DEADS</div>
            <div>{props.timeUnitPlayed} TIME UNITS PLAYED</div>
            <div className="flex flex-row endbtncontainer pt-5">
              <div className="pr-5">
                <div className="gameOverIcon">
                  <button onClick={restartGame}>
                    <img src={Restart}></img>
                  </button>
                </div>
              </div>
              <div>
                <div className="gameOverIcon">
                  <button onClick={exitGame}>
                    <img src={Exit}></img>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default GameEnd;
