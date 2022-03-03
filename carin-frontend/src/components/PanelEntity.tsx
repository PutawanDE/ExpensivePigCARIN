import SelectEntity from "./SelectEntity"
import '../css/Panel.css';
import grabicon from "../assets/grab.png";
import anti1 from "../assets/A1.gif";
import anti2 from "../assets/A2.gif";
import anti3 from "../assets/A3.gif";
import virus1 from "../assets/V1.gif";
import virus2 from "../assets/V2.gif";
import virus3 from "../assets/V3.gif";
import cellnull from "../assets/bk.png";


const dataAnti1   = {
  img: anti1,
  x: -1,
  y: -1,
  type: "A1",
  hp: "100",
  action: "none",
  toposX: -1,
  toposY: -1 
}

const dataAnti2   = {
  img: anti2,
  x: -1,
  y: -1,
  type: "A2",
  hp: "100",
  action: "none",
  toposX: -1,
  toposY: -1 
}
const dataAnti3   = {
  img: anti3,
  x: -1,
  y: -1,
  type: "A3",
  hp: "100",
  action: "none",
  toposX: -1,
  toposY: -1 
}

const dataVirus1  = {
  img: virus1,
  x: -1,
  y: -1,
  type: "V1",
  hp: "20",
  action: "none",
  toposX: -1,
  toposY: -1 
}
const dataVirus2  = {
  img: virus2,
  x: -1,
  y: -1,
  type: "V2",
  hp: "30",
  action: "none",
  toposX: -1,
  toposY: -1 
}
const dataVirus3  = {
  img: virus3,
  x: -1,
  y: -1,
  type: "V3",
  hp: "40",
  action: "none",
  toposX: -1,
  toposY: -1 
}

const dataMove  = {
  img: cellnull,
  x: -1,
  y: -1,
  type: "MOVE",
  hp: "null",
  action: "none",
  toposX: -1,
  toposY: -1 
}

const PanelEntity = () => {

  return (
    

      <div className=" SelectEntity my-4 flex space-x-4">
        <div className=" panelEntity ">
        <SelectEntity entitydata={dataAnti1}  img ={anti1} type="anti_1" />
        <SelectEntity entitydata={dataAnti2}  img ={anti2} type="anti_2"/>
        <SelectEntity entitydata={dataAnti3}  img ={anti3} type="anti_3"/>
      </div>
      <div className=" panelEntity ">
        <SelectEntity entitydata={dataVirus1}  img ={virus1} type="virus_1"/>
        <SelectEntity entitydata={dataVirus2}  img ={virus2} type="virus_2"/>
        <SelectEntity entitydata={dataVirus3}  img ={virus3} type="virus_3"/>
      </div>
      <div className=" panelEntity ">
      <SelectEntity  entitydata={dataMove}  img ={grabicon} type="MOVE"/>
      </div>
      </div>
      
    
  )
}

export default PanelEntity
