import { useState, useCallback } from 'react';
import { Link } from 'react-router-dom';

import { sendRestart } from '../../../api/GameAPI';

import pause from '../assets/pause.png';
import speed from '../assets/speed.png';
import speed05 from '../assets/speed05.png';
import speed1 from '../assets/speed1.png';
import speed2 from '../assets/speed2.png';
import restart from '../assets/restart.png';
import exit from '../assets/exit.png';

const Utility = () => {
  const [speedUse, SetSpeedUse] = useState('X1');

  const sendPause = () => {
    console.log('pasue');
  };
  const setSpeed = useCallback(() => {
    console.log('set speed');
    if (speedUse === 'X2') {
      SetSpeedUse('X05');
      console.log('X05');
    } else if (speedUse === 'X05') {
      SetSpeedUse('X1');
      console.log('X01');
    } else if (speedUse === 'X1') {
      SetSpeedUse('X2');
      console.log('X2');
    }
  }, [speedUse]);

  const sentExit = () => {
    console.log('exit');
  };

  const speedShow = () => {
    if (speedUse === 'X2') {
      return speed2;
    } else if (speedUse === 'X05') {
      return speed05;
    } else if (speedUse === 'X1') {
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
      window.location.replace('/SelectModes ');
    }
  };

  return (
    <div className="pr-5">
      <div className="flex  space-x-3">
        <button onClick={sendPause}>
          <img className="selectIcon icon" src={pause} />
        </button>
        <button onClick={setSpeed}>
          <img className="selectIcon icon" src={speedShow()} />
        </button>
        <button onClick={restartGame}>
          <img className="selectIcon icon" src={restart} />
        </button>
        <button onClick={gotoHome}>
          <img className="selectIcon icon" src={exit} onClick={sentExit} />
        </button>
      </div>
    </div>
  );
};

export default Utility;
