import './Start.css';

import { GameStatus } from '../gameloop/stores/GameStatus';

import { Link } from 'react-router-dom';

import Background from '../../elements/start/StartBackground.gif';
import StartBtn from '../../elements/start/StartBtn.gif';

import useSound from 'use-sound';
import lazysong from '../sound/lazysong.mp3';

function Start() {
  const isInMenu = GameStatus.useState((s) => s.GameStatusData.isInMenu);

  const [playlazy] = useSound(lazysong, {
    loop: true
  });

  if (!isInMenu) {
    playlazy();
  }

  const startBtnHandler = () => {
    GameStatus.update((s) => {
      s.GameStatusData.isInMenu = true;
    });
  };

  return (
    <div className="background startBackgroundColor">
      <Link to="/SelectModes">
        {/*// on Start setup sockjs session id */}

        <button onClick={startBtnHandler} className="content-center startbutton">
          <img src={StartBtn} className="startbuttonpicture" />
        </button>
      </Link>
      <img src={Background} className="center fit" alt="Hello World" />
    </div>
  );
}

export default Start;
