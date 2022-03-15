import './SelectConfiguration.css'
import { useState } from "react";
import { Link } from "react-router-dom";
import AntigenConfig from "../../../elements/configuration/AntigenConfig.png"
import GameConfig from "../../../elements/configuration/GameConfig.png"
import VirusConfig from "../../../elements/configuration/VirusConfig.png"



function SelectConfiguration() {
  const [selectbox, setIsselectbox] = useState("");

  const [isOpen, setIsOpen] = useState(false);
  const togglePopup = () => {
    setIsOpen(!isOpen);
  }

  const [config, setConfig] = useState('');

  const handleChange = (event: any) => {
    const value = event.target.value;
    setConfig(value);
  };

  const handleSave = (event: any) => {
    setIsOpen(false);
    event.preventDefault();
  }

  const ConfigForm = (props: any) => {
    return (
      <div className="config-popup-box">
        <div className="config-text-box">
          <span className="close-icon" onClick={props.handleClose}></span>
          {props.content}
        </div>
      </div>
    );
  };

  return (
    <div className="">
      <div className="parent flex flex-col justify-center center">
        <div className="configBorder font-bold text-5xl py-6">
          Set Configuration
        </div>
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
        <div className="py-4">
          <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-4 px-10 rounded-full">
            Start Game
          </button>
        </div>
      </div>




      {
        isOpen && <ConfigForm content={
          <div className="config-container  flex h-screen w-screen bg-neutral-900/50 z-50  py-20 textboxBackground">
            <div className="">
              <div className="flex flex-col pt-5 pb-10 ">
                <div className="center text-2xl font-bold">
                  Game Configuration
                </div>
                <form onSubmit={handleChange} className="center py-6">
                  <textarea
                    name={selectbox}
                    onChange={handleChange}
                    rows={15}
                    cols={80}
                    className="fixedtextarea focus:ring ring-pink-500 focus:border-rose-700 
              focus:outline-none px-4 py-4 border-dashed border-4 border-rose-500 rounded-3xl"
                  />
                </form>
                <div className="center">
                  <div className="flex flex-row justify-center">
                    <div className="px-2">

                      <button type="submit"
                        className="bg-green-500 hover:bg-green-700 text-white 
                font-bold py-2 px-8 rounded-full text-black text-lg"
                        onClick={handleSave}>
                        Submit
                      </button>
                    </div>

                    <div className="px-2">
                      <button
                        className="bg-red-500 hover:bg-red-700 text-white 
                font-bold py-2 px-8 rounded-full text-black text-lg">
                        Clear
                      </button>
                    </div>
                  </div>
                </div>
              </div>

            </div>
          </div>
        }
          handleClose={togglePopup}
        />
      }
    </div>




  )
}

export default SelectConfiguration;