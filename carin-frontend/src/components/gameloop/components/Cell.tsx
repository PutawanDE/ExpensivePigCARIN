import { BodyStore } from "../stores/BodyStore"
import cell from "../assets/cell.png";
import $ from 'jquery';
import { commandStore } from "../eventCenter"
import './Cell.css';
import cellnull from "../assets/bk.png";


type CellProps = {

  x: number;
  y: number;
  type?: string;
  hp?: string;
  action?: string;
  toposX?: number;
  toposY?: number;
  callmove?: (x: number, y: number, order: number) => void;
}

const resetToMoveMode = {
  img: cellnull,
  x: -1,
  y: -1,
  type: "MOVE",
  hp: "null",
  action: "null",
  toposX: -1,
  toposY: -1
}

const Cell = ({ x, y, type, hp, action, toposX, toposY, callmove }: CellProps) => {

  const state = BodyStore.useState()
  const tcommand = commandStore.useState()
  const compute = BodyStore.useState()
  



  const addEntity = () => {


    if (state.SelectEntity.type === "MOVE" && state.Cell[y][x].img !== cellnull) {   // pick entity move
      if (callmove) callmove(x, y, 0);
    }
    else if (state.SelectEntity.type === "MOVE" && state.Cell[y][x].img === cellnull) { // drop move entity  
      if (callmove) callmove(x, y, 1);
    }
    else if (state.SelectEntity.type !== "MOVE" && state.Cell[y][x].img === cellnull) { // ask want buy&place
      // update      
// sent SelectEntity x y credit




      // BodyStore.update(state => {
      //   state.Cell[y][x] = state.SelectEntity;
      //   state.Cell[y][x].x = x;
      //   state.Cell[y][x].y = y
      // })
      // sent pos
      BodyStore.update(state => { state.SelectEntity = resetToMoveMode })
      BodyStore.update(state => { state.pointer = [x,y] })
      console.log("new")

    }
    
    // console.log({ x, y })
    // console.log("select")
    console.log(state.pointer);
    // console.log("cell")
    // console.log(state.Cell[y][x]);


  }

  const computeRingSize = () => {
    if (state.SelectEntity.type === "MOVE" && state.pointer[0] === x &&  state.pointer[1] === y ) {
      if (  state.Cell[y][x].img !== cellnull ) {
        return "ring-8 ring-red-400"
      }else if(state.Cell[y][x].img === cellnull &&  tcommand.commandData.pos_use ){
        console.log("red")
        return "ring-8 ring-green-400"
      }
    }
    return ""
  }


  const dataShow = () => {
    return < >
      {state.Cell[y][x].img === cellnull ? (
        <></>
      ) : (<>
        <>{state.Cell[y][x].type + " hp:" + state.Cell[y][x].hp} </>
        <>{"x:" + state.Cell[y][x].x + " y:" + state.Cell[y][x].y}</>
        <>{"    a:" + state.Cell[y][x].action}</>
        <>{"  tx:" + state.Cell[y][x].toposX + " ty:" + state.Cell[y][x].toposY}</>
      </>
      )}
    </ >

  }

  return (

    <td className={ `${computeRingSize()} eachcell cursor-pointer font `}
      draggable="false" onClick={addEntity}
    >

      <div className="container">
        <span className="details  ">{dataShow()} </span>

        <img src={cell} className="image" />
        <img src={state.Cell[y][x].img} className="overlay" />
      </div>


    </td >

  )
}

export default Cell
