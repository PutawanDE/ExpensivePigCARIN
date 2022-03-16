import { useState, useCallback } from 'react';
import { Link } from 'react-router-dom';

import { sendRestart, sendSetSpeed } from '../../../api/GameAPI';

import pause from '../assets/pause.png';
import speed05 from '../assets/speed05.png';
import speed1 from '../assets/speed1.png';
import speed2 from '../assets/speed2.png';
import restart from '../assets/restart.png';
import resume from '../assets/resume.png';
import exit from '../assets/exit.png';

let resetSpeed: () => void;

const Utility = () => {
  const [speedUse, SetSpeedUse] = useState(1.0);
  const [isPause, setIsPause] = useState(false);

  const sendPause = () => {
    console.log('pause');
    sendSetSpeed(0);
    setIsPause(true);
  };

  resetSpeed = () => {
    SetSpeedUse(1.0);
    setIsPause(false);
  }

  const sendResume = () => {
    console.log('resume');
    setIsPause(false);
    sendSetSpeed(speedUse);
  };

  const setSpeed = useCallback(() => {
    setIsPause(false);
    console.log('set speed');
    if (speedUse === 2.0) {
      SetSpeedUse(0.5);
      sendSetSpeed(0.5);
      console.log('X05');
    } else if (speedUse === 0.5) {
      SetSpeedUse(1.0);
      sendSetSpeed(1.0);
      console.log('X01');
    } else if (speedUse === 1.0) {
      SetSpeedUse(2.0);
      sendSetSpeed(2.0);
      console.log('X2');
    }
  }, [speedUse]);

  const speedShow = () => {
    if (speedUse === 2.0) {
      return speed2;
    } else if (speedUse === 0.5) {
      return speed05;
    } else if (speedUse === 1.0) {
      return speed1;
    }
  };

  const restartGame = () => {
    if (window.confirm('Do you want to restart this game?')) {
      sendRestart();
    }
  };

  const gotoHome = () => {
    if (window.confirm('Do you want to exit? This game will be lost.')) {
      console.log('exit')
      window.location.replace('/SelectModes ');
    }
  };

  return (
    <div className="pr-5">
      <div className="flex  space-x-3">
        {isPause ? (
          <button onClick={sendResume}>
            <img className="selectIcon icon" src={resume} />
          </button>
        ) : (
          <button onClick={sendPause}>
            <img className="selectIcon icon" src={pause} />
          </button>
        )}
        <button onClick={setSpeed}>
          <img className="selectIcon icon" src={speedShow()} />
        </button>
        <button onClick={restartGame}>
          <img className="selectIcon icon" src={restart} />
        </button>
        <button onClick={gotoHome}>
          <img className="selectIcon icon" src={exit} />
        </button>
      </div>
    </div>
  );
};

export { resetSpeed }
export default Utility;
