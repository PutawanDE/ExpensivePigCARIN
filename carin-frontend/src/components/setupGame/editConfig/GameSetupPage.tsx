import { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { startCustomGame } from '../../../api/GameAPI';
import AntigenConfig from '../../../elements/configuration/AntigenConfig.png';
import GameConfig from '../../../elements/configuration/GameConfig.png';
import VirusConfig from '../../../elements/configuration/VirusConfig.png';
import Spinner from '../../loading/Spinner';
import { GameSetupStore } from '../GameSetupStore';

import GameConfigPopup from './GameConfigPopup';
import './GameSetupPage.css';

let gotoCustomGame: () => void;
let showErrSetupPage: (errorMsg: any) => void;

function GameSetupPage() {
  const [isConfigOpen, setConfigIsOpen] = useState(false);
  const [isLoading, setIsloading] = useState(false);

  const setup = GameSetupStore.useState();

  const togglePopup = () => {
    setConfigIsOpen(!isConfigOpen);
  };

  const navigate = useNavigate();

  const start = (): void => {
    setIsloading(true);
    startCustomGame(setup);
  };

  gotoCustomGame = () => {
    navigate('/game');
  };

  showErrSetupPage = (errorMsg: any) => {
    setIsloading(false);
    alert(errorMsg);
  };

  return (
    <div className="">
      {isLoading && <Spinner />}
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
        <div className="py-4 flex justify-center">
          <div className="px-10">
            <button
              className="text-2xl bg-red-500 hover:bg-red-700 text-white 
                        font-bold py-4 px-10 rounded-full">
              ⏪ Back
            </button>
          </div>
          <div className="px-10">
            <button
              className="text-2xl bg-blue-500 hover:bg-blue-700 text-white 
                        font-bold py-4 px-10 rounded-full"
              onClick={start}>
              ▶️ Start Game
            </button>
          </div>
        </div>
      </div>

      {isConfigOpen && <GameConfigPopup handleClose={togglePopup} />}
    </div>
  );
}

export { gotoCustomGame, showErrSetupPage as showErrCustomGame };
export default GameSetupPage;
