import SelectEntity from "./SelectEntity"
import '../css/Panel.css';
import anti1 from "../assets/A1.gif";
import anti2 from "../assets/A2.gif";
import anti3 from "../assets/A3.gif";
import virus1 from "../assets/V1.gif";
import virus2 from "../assets/V2.gif";
import virus3 from "../assets/V3.gif";
import cellnull from "../assets/bk.png";
import grabicon from "../assets/grab.png";
 
const PanelEntity = () => {

  const dataAnti1   = {
    img: anti1,
    x: 0,
    y: 0,
    type: "A1",
    hp: "100",
    action: "hi",
    toposX: 0,
    toposY: 0 
  }

  const dataAnti2   = {
    img: anti2,
    x: 0,
    y: 0,
    type: "A2",
    hp: "100",
    action: "hi",
    toposX: 0,
    toposY: 0 
  }
  const dataAnti3   = {
    img: anti3,
    x: 0,
    y: 0,
    type: "A3",
    hp: "100",
    action: "hi",
    toposX: 0,
    toposY: 0 
  }

  const dataVirus1  = {
    img: virus1,
    x: 3,
    y: 3,
    type: "V1",
    hp: "20",
    action: "bye",
    toposX: 0,
    toposY: 0 
  }
  const dataVirus2  = {
    img: virus2,
    x: 3,
    y: 3,
    type: "V2",
    hp: "30",
    action: "go",
    toposX: 0,
    toposY: 0 
  }
  const dataVirus3  = {
    img: virus3,
    x: 1,
    y: 1,
    type: "V3",
    hp: "40",
    action: "run",
    toposX: 0,
    toposY: 0 
  }

  const dataNull  = {
    img: cellnull,
    x: 0,
    y: 0,
    type: "M",
    hp: "null",
    action: "null",
    toposX: 0,
    toposY: 0 
  }





  return (
    

      <div className=" SelectEntity my-4 flex space-x-4">
        <div className=" panelEntity ">
        <SelectEntity entitydata={dataAnti1}  img ={anti1}/>
        <SelectEntity entitydata={dataAnti2}  img ={anti2}/>
        <SelectEntity entitydata={dataAnti3}  img ={anti3}/>
      </div>
      <div className=" panelEntity ">
        <SelectEntity entitydata={dataVirus1}  img ={virus1}/>
        <SelectEntity entitydata={dataVirus2}  img ={virus2}/>
        <SelectEntity entitydata={dataVirus3}  img ={virus3}/>
      </div>
      <div className=" panelEntity ">
      <SelectEntity  entitydata={dataNull}  img ={grabicon} />
      </div>
      </div>
      
    
  )
}

export default PanelEntity
