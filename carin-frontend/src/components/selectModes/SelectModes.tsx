import './SelectModes.css';

import { Link, useNavigate } from 'react-router-dom';
import Spinner from '../loading/Spinner';
import DefaultMode from '../../elements/selectModes/DefaultMode.png';
import CustomMode from '../../elements/selectModes/CustomMode.png';
import { startDefaultGame } from '../../api/GameAPI';
import { useState } from 'react';

let gotoGame: () => void;
let showError: (errorMsg: any) => void;

function SelectModes() {
  const [isLoading, setIsloading] = useState(false);

  const navigate = useNavigate();

  const start = (): void => {
    setIsloading(true);
    startDefaultGame();
  };

  gotoGame = () => {
    navigate('/game');
  };

  showError = (errorMsg: any) => {
    setIsloading(false);
    alert(errorMsg);
  };

  return (
    <>
      {isLoading && <Spinner />}
      <div className="parent">
        <div className="flex flex-row justify-center child">
          <div className="pr-10">
            <button onClick={start}>
              <img src={DefaultMode} className="selectModeSize " />
            </button>
          </div>
          <div className="pl-10">
            <Link to="/SelectConfiguration">
              <button>
                <img src={CustomMode} className="selectModeSize" />
              </button>
            </Link>
          </div>
        </div>
      </div>
    </>
  );
}

export { gotoGame, showError };
export default SelectModes;
