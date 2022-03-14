import { useState } from "react";
import { SetupGame } from "../setupGameSender"

import ReactDOM from "react-dom";
import { Link } from "react-router-dom";

import NextBTN from "../assets/NextBTN.png";
import './configCenter.css';

function ConfigSetup() {
  const [config, setConfig] = useState("");
  const setup = SetupGame.useState()


  const handleChange = (event: any) => {
    setConfig(event.target.value)
  }

  const handleSubmit = (event: any) => {
    event.preventDefault();
    console.log(config);
  }
  const save = () => {
    setup.setupData.config = config
  }

  return (
    <>

      <div>
        <textarea value={config} onChange={handleChange} className='configbox'
          rows={20}
          cols={150} />
      </div>

      <button className="content-center startbutton" onClick={handleSubmit} >
        <img src={NextBTN} alt="my image" className="startbuttonpicture" />
      </button>
      <Link to="/editAnti">

        {/*// on Start setup sockjs session id */}

        <button className="content-center startbutton"  onClick={save } >
          <img src={NextBTN} alt="my image" className="startbuttonpicture" />
        </button>
      </Link>
    </>

  )
}


export default ConfigSetup;
