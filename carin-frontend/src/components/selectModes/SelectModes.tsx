import './SelectModes.css';

import { useNavigate } from 'react-router-dom';
import Spinner from '../loading/Spinner';
import DefaultMode from '../../elements/selectModes/DefaultMode.png';
import CustomMode from '../../elements/selectModes/CustomMode.png';
import { GameSetup, startDefaultGame } from '../../api/GameAPI';
import { useState } from 'react';
import { defaultGameSetup, GameSetupStore } from '../setupGame/GameSetupStore';

let gotoDefaultGame: () => void;
let showErrModePage: (errorMsg: any) => void;

function SelectModes() {
  const [isLoading, setIsloading] = useState(false);

  const navigate = useNavigate();

  const start = (): void => {
    setIsloading(true);
    startDefaultGame();
  };

  gotoDefaultGame = () => {
    navigate('/game');
  };

  showErrModePage = (errorMsg: any) => {
    setIsloading(false);
    alert(errorMsg);
  };

  const getDefaultSetup = () => {
    setIsloading(true);
    fetch('http://localhost:8080/setup')
      .then((resp: any) => {
        resp.json().then(function (data: GameSetup) {
          defaultGameSetup.gameConfig = data.gameConfig;
          defaultGameSetup.gameConfig = data.gameConfig;
          defaultGameSetup.antiGeneticCodes = data.antiGeneticCodes;
          defaultGameSetup.virusGeneticCodes = data.virusGeneticCodes;

          GameSetupStore.update((s) => {
            console.log('Default Game Setup:', data);

            s.gameConfig = data.gameConfig;
            s.antiGeneticCodes = data.antiGeneticCodes;
            s.virusGeneticCodes = data.virusGeneticCodes;
          });

          navigate('/gameSetup');
        });
      })
      .catch((err) => {
        showErrModePage(err);
      });
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
            <button onClick={getDefaultSetup}>
              <img src={CustomMode} className="selectModeSize" />
            </button>
          </div>
        </div>
      </div>
    </>
  );
}

export { gotoDefaultGame, showErrModePage as showErrDefaultGame };
export default SelectModes;
