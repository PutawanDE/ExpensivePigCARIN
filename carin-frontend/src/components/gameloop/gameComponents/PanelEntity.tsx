import SelectEntity from "./SelectEntity"
import './Panel.css';
import grabicon from "../assets/grab.png";
import anti1 from "../assets/A1.gif";
import anti2 from "../assets/A2.gif";
import anti3 from "../assets/A3.gif";
import virus1 from "../assets/V1.gif";
import virus2 from "../assets/V2.gif";
import virus3 from "../assets/V3.gif";
import cellnull from "../assets/bk.png";

import produceEntity from "../EntityFactory";
import { CellProps } from "../stores/BodyStore";

const dataMove : CellProps = {
  img: cellnull,
  x: -1,
  y: -1,
  type: "MOVE",
  hp: 0,
  action: "none",
  toposX: -1,
  toposY: -1 
}

const PanelEntity = () => {

  return (
    

      <div className=" SelectEntity my-4 flex space-x-4">
        <div className=" panelEntity ">
        <SelectEntity entitydata={produceEntity("A1")}  img ={anti1} type="anti_1" />
        <SelectEntity entitydata={produceEntity("A2")}  img ={anti2} type="anti_2"/>
        <SelectEntity entitydata={produceEntity("A3")}  img ={anti3} type="anti_3"/>
      </div>
      <div className=" panelEntity ">
        <SelectEntity entitydata={produceEntity("V1")}  img ={virus1} type="virus_1"/>
        <SelectEntity entitydata={produceEntity("V2")}  img ={virus2} type="virus_2"/>
        <SelectEntity entitydata={produceEntity("V3")}  img ={virus3} type="virus_3"/>
      </div>
      <div className=" panelEntity ">
      <SelectEntity  entitydata={dataMove}  img ={grabicon} type="MOVE"/>
      </div>
      </div>
      
    
  )
}

export default PanelEntity
