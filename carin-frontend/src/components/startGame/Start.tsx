import './Start.css';

import { GameStatus } from '../gameloop/stores/GameStatus';

import { Link } from "react-router-dom";

import Background from "../../elements/start/StartBackground.gif";
import StartBtn from "../../elements/start/StartBtn.gif";

import useSound from "use-sound";
import lazysong from '../sound/lazysong.mp3';  

function Start() {

    const gameStatus = GameStatus.useState();

    const [playlazy] = useSound(lazysong, {
        loop: true,
      });
    if(!gameStatus.GameStatusData.isPlay){
        playlazy()
    }
    
    return (
        <div className="background startBackgroundColor">
            <Link to="/SelectModes">


                {/*// on Start setup sockjs session id */}

                <button className="content-center startbutton">
                    <img src={StartBtn} className="startbuttonpicture" />
                </button>
            </Link>
            <img src={Background} className="center fit" alt="Hello World" />
        </div>
    );
}

export default Start;