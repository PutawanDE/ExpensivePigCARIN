import './Start.css';


import { Link } from "react-router-dom";

import Background from "../../elements/start/StartBackground.gif";
import StartBtn from "../../elements/start/StartBtn.gif";
function Start() {

    return (
        <div className="background">
            <Link to="/editantigen">
                <button className="content-center startbutton">
                    <img src={StartBtn} alt="my image" className="startbuttonpicture" />
                </button>
            </Link>
            <img src={Background} className="center fit" alt="Hello World" />
        </div>
    );
}

export default Start;