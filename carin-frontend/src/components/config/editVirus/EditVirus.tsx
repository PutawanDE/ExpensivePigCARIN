import './EditVirus.css';
import { Link } from "react-router-dom";

import V1 from "../../../elements/editAntibody/AGA.gif";
import V2 from "../../../elements/editAntibody/AGB.gif";
import V3 from "../../../elements/editAntibody/AGC.gif";
import NextBTN from "../../../elements/editAntibody/NextBTN.png";

function EditVirus() {

  return (
    <div className="background">
      <div className="container mx-auto py-10 text-center text-5xl font-bold">
              EDIT ANTIGEN GENETIC CODE
      </div>
      <div className="flex flex-row ">
        <div className="basis-1/3 px-20">
          <button className="center"><img src={V1} alt="" className="geneticbuttonpicture " /></button>
        </div>
        <div className="basis-1/3 px-20">
          <button className="center"><img src={V2} alt="" className="geneticbuttonpicture " /></button>
        </div>
        <div className="basis-1/3 px-20">
          <button className="center"><img src={V3} alt="" className="geneticbuttonpicture " /></button>
        </div>
      </div>
      <div className="right pr-12 pt-3">
        <Link to="/game">
          
            {/* save virus-genetic string[]
              then send anti&virus genetic 
            */}

        <button className="nextBTN"><img src={NextBTN} alt="" className="geneticbuttonpicture " /></button>
        </Link>
      </div>
    </div>
  );
}

export default EditVirus;