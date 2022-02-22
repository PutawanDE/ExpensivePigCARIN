import { BodyStore } from "../stores/BodyStore"
import cell from "../assets/cell.png";
import cellnull from "../assets/bk.png";
import $ from 'jquery';
import '../css/Cell.css';

import anti1 from "../assets/A1.gif";
import virus1 from "../assets/V1.gif";


type CellProps = {

  x: number;
  y: number;
  type: string;
  hp: string;
  action: string;
  toposX: number;
  toposY: number;
}



const Cell = ({ x, y, type, hp, action, toposX, toposY }: CellProps) => {

  const state = BodyStore.useState()
  const compute = BodyStore.useState()





  const addEntity = () => {
    const dataNull = {
      img: cellnull,
      x: 0,
      y: 0,
      type: "null",
      hp: "null",
      action: "null",
      toposX: 0,
      toposY: 0
    }
 
    if (state.SelectEntity.img === cellnull && state.Cell[y][x].img != cellnull) {   // ask want move

      // call move fn

      // sent move
      console.log("move")
    }

    else if (state.SelectEntity.img != cellnull && state.Cell[y][x].img === cellnull) { // ask want place
      // update      
      BodyStore.update(state => { state.Cell[y][x] = state.SelectEntity ; state.Cell[y][x].x = x ; state.Cell[y][x].y = y})

      // sent pos
      BodyStore.update(state => { state.SelectEntity = dataNull })
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
        <>{ state.Cell[y][x].type + " hp:" + state.Cell[y][x].hp } </>
        <>{"x:" +state.Cell[y][x].x + " y:" + state.Cell[y][x].y }</>
        <>{"    a:" +state.Cell[y][x].action  }</>
        <>{"  tx:" +state.Cell[y][x].toposX + " ty:" + state.Cell[y][x].toposY }</>
        </>
      )}
    </ >

  }

  return (
    // <td className="cursor-pointer"
    //   draggable="false" onClick={dragDraw}
    //   style={{ backgroundColor: state.canvas[y][x] }
    //
    //     >
    //
    // </td >
    <td className="eachcell cursor-pointer font "
      draggable="true" onClick={addEntity}
    >


      <div className="container">
      <span className="details  ">{dataShow()} </span>
        
        <img src={cell} className="image" />
        <img src={state.Cell[y][x].img} className="overlay" />
      </div>


    </td >

    // <img  src={virus1} className="w-16 h-16 cursor-pointer"
    //   draggable="false"  onClick={dragDraw}
    //   style={{ backgroundColor: state.canvas[y][x] }
    //   }>



  )
}

export default Cell




// import { PixelPainterStore, updateCellColors } from "../stores/PixelPainterStore";
// import React, { useCallback, useState } from "react";
//
// export type CellProps = {
//   x: number;
//   y: number;
//
// };
//
// const Cell = ({ x, y }: CellProps) => {
//   const state = PixelPainterStore.useState();
//   const [color, setColor] = useState("white");
//
//   const paintCanvas = useCallback(() => {
//     setColor(state.SelectColor);
//   }, [state.SelectColor]);
//
//   return (
//     <td
//       className="w-6 h-6 cursor-pointer"
//       style={{ backgroundColor: color }}
//       onClick={paintCanvas}
//       draggable={true}
//       onDragOver={paintCanvas}
//     ></td>
//   );
// };
//
// export default Cell;
