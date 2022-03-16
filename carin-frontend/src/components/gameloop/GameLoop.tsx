import React, { useCallback, useEffect, useState } from 'react';
import './GameLoop.css';
import BodyMap from './gameComponents/BodyMap';
import SelectPanel from './gameComponents/SelectPanel';
import Credit from './gameComponents/Credit';

import Utility from './gameComponents/Utility';
import GameEnd from './gameComponents/GameEnd';
import { GameStatus } from './stores/GameStatus';

function GameLoop() {
  useEffect(() => {
    GameStatus.update((s) => {
      s.GameStatusData.isPlay = true;
    });
  }, []);

  const gameStatus = GameStatus.useState();

  return (
    <div className="gameColor">
      <div>
        <div className="GameLoop">
          <div className="menubar pt-1 pb-6">
            <Credit />
            <SelectPanel />
            <Utility />
          </div>
          <BodyMap />
        </div>
      </div>
      {gameStatus.GameStatusData.isGameEnd && (
        <GameEnd
          status={gameStatus.GameStatusData.status}
          virusDeadCount={gameStatus.GameStatusData.virusDeadCount}
          antiDeadCount={gameStatus.GameStatusData.antiDeadCount}
          timeUnitPlayed={gameStatus.GameStatusData.timeUnitPlayed}
          action={'gameover'}
        />
      )}
    </div>
  );
}

export default GameLoop;
