import './SelectAntigen.css';
import Background from "../../elements/start/NormalBackground.gif";
import StartBtn from "../../elements/start/StartBtn.gif";

import AGA from "../../elements/selectantigen/AGA.gif";
import AGB from "../../elements/selectantigen/AGB.gif";
import AGC from "../../elements/selectantigen/AGC.gif";
import NextBTN from "../../elements/selectantigen/NextBTN.png";

function SelectAntigen() {

  return (
    <div className="background">
      <div className="container mx-auto py-12 text-center text-3xl font-bold">
        EDIT GENETIC CODE
      </div>
      <div className="flex flex-row ">
        <div className="basis-1/3">
          <button className="center"><img src={AGA} alt="" className="geneticbuttonpicture " /></button>
        </div>
        <div className="basis-1/3">
          <button className="center"><img src={AGB} alt="" className="geneticbuttonpicture " /></button>
        </div>
        <div className="basis-1/3">
          <button className="center"><img src={AGC} alt="" className="geneticbuttonpicture " /></button>
        </div>
      </div>
      <div className="right pr-12 py-12">
        <button className="nextBTN"><img src={NextBTN} alt="" className="geneticbuttonpicture " /></button>
      </div>
    </div>
  );
}

export default SelectAntigen;