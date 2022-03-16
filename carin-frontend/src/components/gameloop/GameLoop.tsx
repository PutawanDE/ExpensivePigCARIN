import React, { useCallback, useEffect, useState } from 'react';
import './GameLoop.css';
import BodyMap from './gameComponents/BodyMap';
import SelectPanel from './gameComponents/SelectPanel';
import Credit from './gameComponents/Credit';

import Utility from './gameComponents/Utility';
import GameEnd from './gameComponents/GameEnd';
import { GameStatus } from './stores/GameStatus';
import Trophy from "../../elements/result/trophy.gif";
import Restart from "./assets/restart.png"
import Exit from "./assets/exit.png"



function GameLoop() {
  useEffect(() => {
    GameStatus.update(s =>{ s.GameStatusData.isPlay = true })
  }, [])


  const gameStatus = GameStatus.useState()

  return (
    <div className="gameColor">
      <div>

        <div className='GameLoop' >

          <div className="menubar pt-1 pb-6">
            <Credit />
            <SelectPanel />
            <Utility />
          </div>
          <BodyMap />
        </div>
      </div >
      {
        gameStatus.GameStatusData.isGameEnd && <GameEnd content={
          <div className="center text-2xl font-bold py-8 text-white">
            <div className="endcenter">
              <div className="text-4xl">
                GAME OVER
              </div>
              <div>
                <img src={Trophy} className="trophyEnd" />
              </div>
              <div>
                YOU {gameStatus.GameStatusData.Tiile}
              </div>
              <div>
                YOURE SCORE IS {gameStatus.GameStatusData.Content}
              </div>
              <div className="flex flex-row endbtncontainer pt-5">
                <div className="pr-5">
                  <div className="gameOverIcon">
                    <img src={Restart}></img>
                  </div>
                </div>
                <div>
                  <div className="gameOverIcon">
                    <img src={Exit}></img>
                  </div>
                </div>


              </div>

            </div>



          </div>
        }
        />
      }
    </div>

  );
}

export default GameLoop;
