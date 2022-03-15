import './SelectModes.css'

import { Link } from "react-router-dom";
import DefaultMode from "../../elements/selectModes/DefaultMode.png"
import CustomMode from "../../elements/selectModes/CustomMode.png"


function SelectModes() {
    return (
        <div className="parent">
            <div className="flex flex-row justify-center child">

                <div className="pr-10">
                    <Link to="/">
                        <button>
                            <img src={DefaultMode} className="selectModeSize " />
                        </button>
                    </Link>
                </div>
                <div className="pl-10">
                    <Link to="/SelectConfiguration">
                        <button>
                            <img src={CustomMode} className="selectModeSize" />
                        </button>
                    </Link>
                </div>

            </div>
        </div>

    )
}

export default SelectModes;