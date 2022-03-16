import './GameLoop.css';
import BodyMap from './gameComponents/BodyMap';
import SelectPanel from './gameComponents/SelectPanel';
import Credit from './gameComponents/Credit';

import Utility from './gameComponents/Utility';
import GameEnd from './gameComponents/GameEnd';

import { FpsView } from 'react-fps';

import { GameStatus } from './stores/GameStatus';



function GameLoop() {

  const gameStatus = GameStatus.useState()

  return (
    <div>
      <div>

        <div className='GameLoop' >
          <FpsView width={240} height={180} left={1500} top={700} />
          <div className="menubar pb-10 pt-5">
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
