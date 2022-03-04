import './EditAntigen.css';

import AGA from "../../elements/editAntibody/AGA.gif";
import AGB from "../../elements/editAntibody/AGB.gif";
import AGC from "../../elements/editAntibody/AGC.gif";
import NextBTN from "../../elements/editAntibody/NextBTN.png";

function EditAntigen() {

  return (
    <div className="background">
      <div className="container mx-auto py-10 text-center text-5xl font-bold">
              EDIT ANTIGEN GENETIC CODE
      </div>
      <div className="flex flex-row ">
        <div className="basis-1/3 px-20">
          <button className="center"><img src={AGA} alt="" className="geneticbuttonpicture " /></button>
        </div>
        <div className="basis-1/3 px-20">
          <button className="center"><img src={AGB} alt="" className="geneticbuttonpicture " /></button>
        </div>
        <div className="basis-1/3 px-20">
          <button className="center"><img src={AGC} alt="" className="geneticbuttonpicture " /></button>
        </div>
      </div>
      <div className="right pr-12 pt-3">
        <button className="nextBTN"><img src={NextBTN} alt="" className="geneticbuttonpicture " /></button>
      </div>
    </div>
  );
}

export default EditAntigen;