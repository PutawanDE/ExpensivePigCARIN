import './SelectConfiguration.css'

import { Link } from "react-router-dom";
import AntigenConfig from "../../../elements/configuration/AntigenConfig.png"
import GameConfig from "../../../elements/configuration/GameConfig.png"
import VirusConfig from "../../../elements/configuration/VirusConfig.png"


function SelectConfiguration() {
    return (
        <div className="parent flex flex-col justify-center center">
            <div className="configBorder font-bold text-5xl py-6">
                Set Configuration
            </div>
            <div className="px-64">
                <div className="flex flex-row justify-center child">
                    <div className="pr-20">
                        <button>
                            <img src={GameConfig} className="selectModeSize " />
                        </button>
                    </div>
                    <div className="pr-20">
                        <Link to="/">
                            <button>
                                <img src={AntigenConfig} className="selectModeSize " />
                            </button>
                        </Link>
                    </div>
                    <div className="">
                        <Link to="/">
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


    )
}

export default SelectConfiguration;