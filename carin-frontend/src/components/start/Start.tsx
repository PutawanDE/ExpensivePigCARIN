import './Start.css';
import Background from "../../elements/start/StartBackground.gif";
import StartBtn from "../../elements/start/StartBtn.gif";
function Start() {

    return (
        <div className="background">
            <script src="https://cdn.tailwindcss.com"></script>
            <button className="content-center startbutton"><img src={StartBtn} alt="my image" className="startbuttonpicture" /></button>
            <img src={Background} className="center fit" alt="Hello World" />
            <h1 className="text-3xl font-bold underline">
                Hello world!
            </h1>
        </div>
    );
}

export default Start;