import React from 'react';

import { sendBuy } from '../../../api/GameAPI';
import { EventTypes } from '../../../api/EventTypes';

import { CellProps, CellType } from '../CellFactory';
import { BodyStore, InputType } from '../stores/BodyStore';
import { commandStore } from '../eventCenter';

import bg from '../assets/cell.png';

import './Cell.css';

type props = {
  cellProps: CellProps;
  callmove?: (x: number, y: number, order: number) => void;
};

const Cell = (props: props) => {
  const { x, y, type: cellType, hp, action, img: entityImg } = props.cellProps;
  const callmove = props.callmove;

  // console.log('Cell render x: ' + x + ' y: ' + y);

  const input = BodyStore.useState((s) => s.inputType);
  const pointer = BodyStore.useState((s) => s.pointer);
  const tcommand = commandStore.useState();

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

  const dataShow = () => {
    return (
      <>
        {cellType === CellType.empty ? (
          <></>
        ) : (
          <>
            <>{cellType + ' hp:' + hp} </>
            <>{'x:' + x + ' y:' + y}</>
            <>{'    a:' + action}</>
          </>
        )}
      </>
    );
  };

  return (
    <td
      className={`${getRingColor()} eachcell cursor-pointer font `}
      draggable="false"
      onClick={cellOnClickHandler}>
      <div className="container">
        <span className="details  ">{dataShow()} </span>

        <img src={entityImg} className="overlay" />
        <img src={bg} className="image" />
      </div>
    </td>
  );
};

export default React.memo(Cell);
