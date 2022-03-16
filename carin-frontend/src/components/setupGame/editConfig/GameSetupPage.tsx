import { useState } from 'react';
import { Link } from 'react-router-dom';
import AntigenConfig from '../../../elements/configuration/AntigenConfig.png';
import GameConfig from '../../../elements/configuration/GameConfig.png';
import VirusConfig from '../../../elements/configuration/VirusConfig.png';

import GameConfigPopup from './GameConfigPopup';
import './GameSetupPage.css';

function GameSetupPage() {
  const [isConfigOpen, setConfigIsOpen] = useState(false);
  const togglePopup = () => {
    setConfigIsOpen(!isConfigOpen);
  };

  return (
    <div className="">
      <div className="parent flex flex-col justify-center center">
        <div className="gameSetupPage font-bold text-5xl py-6">GAME SETUP</div>
        <div className="px-64">
          <div className="flex flex-row justify-center child">
            <div className="pr-20">
              <button onClick={() => togglePopup()}>
                <img src={GameConfig} className="selectModeSize " />
              </button>
            </div>
            <div className="pr-20">
              <Link to="/editAnti">
                <button>
                  <img src={AntigenConfig} className="selectModeSize " />
                </button>
              </Link>
            </div>
            <div className="">
              <Link to="/editVirus">
                <button>
                  <img src={VirusConfig} className="selectModeSize" />
                </button>
              </Link>
            </div>
          </div>
        </div>
        <div className="py-4">
          <button className="text-2xl bg-blue-500 hover:bg-blue-700 text-white font-bold py-4 px-10 rounded-full editbtn">
            ▶️ Start Game
          </button>
        </div>
      </div>

      {isConfigOpen && <GameConfigPopup handleClose={togglePopup} />}
    </div>
  );
}

export default GameSetupPage;
