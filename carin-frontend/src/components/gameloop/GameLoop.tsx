import React, { useCallback, useEffect, useState } from 'react';
import './GameLoop.css';
import BodyMap from './gameComponents/BodyMap';
import SelectPanel from './gameComponents/SelectPanel';
import Credit from './gameComponents/Credit';

import Utility from './gameComponents/Utility';
import GameEnd from './gameComponents/GameEnd';

import { FpsView } from 'react-fps';

import { GameStatus } from './stores/GameStatus';



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
          <div className="center text-2xl font-bold">
            Game END <br></br>
            {gameStatus.GameStatusData.Tiile} <br></br>
            {gameStatus.GameStatusData.Content}
          </div>
        }
        />
      }
    </div>

  );
}

export default GameLoop;
