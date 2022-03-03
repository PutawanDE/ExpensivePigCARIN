import { BodyStore } from "../stores/BodyStore"
import cell from "../assets/cell.png";
import $ from 'jquery';
import '../css/Cell.css';
import cellnull from "../assets/bk.png";


type CellProps = {

  x: number;
  y: number;
  type?: string;
  hp?: string;
  action?: string;
  toposX?: number;
  toposY?: number;
  callmove?: () => void;
}

const dataMove = {
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
  const compute = BodyStore.useState()





  const addEntity = () => {


    if (state.SelectEntity.type === "MOVE" && state.Cell[y][x].img != cellnull) {   // ask want move


      // call move fn

      // sent move
      console.log("move")
      if(callmove) callmove();

    }

    else if (state.SelectEntity.type != "MOVE" && state.Cell[y][x].img === cellnull) { // ask want place
      // update      
      BodyStore.update(state => {
        state.Cell[y][x] = state.SelectEntity;
        state.Cell[y][x].x = x;
        state.Cell[y][x].y = y
      })
      // sent pos
      BodyStore.update(state => { state.SelectEntity = dataMove })
      console.log("new")

    }

    console.log({ x, y })
    console.log("select")
    console.log(state.SelectEntity);
    console.log("cell")
    console.log(state.Cell[y][x]);


  }


  const dataShow = () => {
    return < >
      {state.Cell[y][x].img == cellnull ? (
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

    <td className="eachcell cursor-pointer font "
      draggable="true" onClick={addEntity}
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
