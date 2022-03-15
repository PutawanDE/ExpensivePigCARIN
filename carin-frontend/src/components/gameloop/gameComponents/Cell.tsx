import React, { useCallback, useEffect, useState } from 'react';
import { sendBuy } from '../../../api/GameAPI';
import { EventTypes } from '../../../api/EventTypes';

import { CellProps, CellType } from '../CellFactory';
import { BodyStore, InputType } from '../stores/BodyStore';
import { commandStore } from '../eventCenter';

import bg from '../assets/cell.png';
import $ from 'jquery';
import './Cell.css';

type props = {
  cellProps: CellProps;
  callmove?: (x: number, y: number, order: number) => void;
};

const Cell = (props: props) => {
  const { x, y, type: cellType, hp, action, shootDir, img: entityImg } = props.cellProps;
  const callmove = props.callmove;

  console.log('Cell render x: ' + x + ' y: ' + y);

  const input = BodyStore.useState((s) => s.inputType);
  const pointer = BodyStore.useState((s) => s.pointer);
  const tcommand = commandStore.useState();

  useEffect(() => {

    computeAnimate();
  }, [])

  const cellOnClickHandler = () => {
    if (input === InputType.MOVE && cellType !== CellType.empty) {
      // pick entity move
      if (callmove) callmove(x, y, 0);
    } else if (input === InputType.MOVE && cellType === CellType.empty) {
      // drop move entity
      if (callmove) callmove(x, y, 1);
    } else if (input !== InputType.MOVE && cellType === CellType.empty) {
      // ask want buy&place
      // update
      // sent SelectEntity x y credit

      // sent pos
      const buyEvent: EventTypes.BuyEvent = {
        kind: parseInt(input.substring(1)) - 1,
        pos: [x, y]
      };

      sendBuy(buyEvent);

      BodyStore.update((state) => {
        state.pointer = [x, y];
      });

      console.log('new of type ' + input);
    }
  };

  const getRingColor = () => {
    if (input === InputType.MOVE && pointer[0] === x && pointer[1] === y) {
      if (cellType !== CellType.empty) {
        // select
        return 'ring-8 ring-red-400';
      } else if (cellType === CellType.empty && tcommand.commandData.isSelected) {
        // place
        return 'ring-8 ring-green-400';
      }
    }
    return '';
  };

  const computeAnimate = useCallback(() => {

    /*     UP("up"), UP_RIGHT("upright"), RIGHT("right"), DOWN_RIGHT("downright"),
        DOWN("down"), DOWN_LEFT("downleft"), LEFT("left"), UP_LEFT("upleft");
    */
    if (action === "shoot0" || action === "shoot1") {
      let dir: number;
      switch (shootDir!) {
        case "UP": dir = 90;
          break;
        case "UP_RIGHT": dir = 125;
          break;
        case "RIGHT": dir = 180;
          break;
        case "DOWN_RIGHT": dir = 225;
          break;
        case "DOWN": dir = 270;
          break;
        case "DOWN_LEFT": dir = 325;
          break;
        case "LEFT": dir = 0;
          break;
        case "UP_LEFT": dir = 45;
          break;
        default: dir = 0;
          break;
      }
  
      // shoot animate
      const shootStyle = {
        transform: 'rotate(' + dir + 'deg)',
  
      } as React.CSSProperties;
      console.log("animate shoot");
      
      return (
        <span className="spark" style={shootStyle} ></span>
      );
    }
  }, [action]);

  const dataShow = () => {
    return (
      <>
        {cellType === CellType.empty ? (
          <></>
        ) : (
            <>{' hp:' + hp} </>
        )}
      </>
    );
  };

  return (
    <td
      className={`${getRingColor()} eachcell cursor-pointer font `}
      draggable="false"
      onClick={cellOnClickHandler}>
      <div className="Cellcontainer" key={action}>
        <span className="details  ">{dataShow()} </span>
        {computeAnimate()}
        <img src={entityImg} className="overlay" />
        <img src={bg} className="image" />
      </div>
    </td>
  );
};

export default React.memo(Cell);
