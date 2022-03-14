import React from 'react';
import { CellProps, BodyStore } from '../stores/BodyStore';
import bg from '../assets/cell.png';
import $ from 'jquery';
import { commandStore } from '../eventCenter';
import './Cell.css';
import cellnull from '../assets/bk.png';

import { sendBuy } from '../../../api/GameAPI';
import { EventTypes } from '../../../api/EventTypes';

const resetToMoveMode: CellProps = {
  img: cellnull,
  x: -1,
  y: -1,
  type: 'MOVE',
  hp: 0,
  action: 'null',
  toposX: -1,
  toposY: -1
};

type props = {
  cellProps: CellProps;
  callmove?: (x: number, y: number, order: number) => void;
};

const Cell = (props: props) => {
  const { x, y, type, hp, action, toposX, toposY, img: entityImg } = props.cellProps;
  const callmove = props.callmove;

  // console.log('Cell render x: ' + x + ' y: ' + y);

  const selectedEntity = BodyStore.useState((s) => s.SelectEntity);
  const pointer = BodyStore.useState((s) => s.pointer);
  const tcommand = commandStore.useState();

  const cellOnClickHandler = () => {
    if (selectedEntity.type === 'MOVE' && type !== 'null') {
      // pick entity move
      if (callmove) callmove(x, y, 0);
    } else if (selectedEntity.type === 'MOVE' && type === 'null') {
      // drop move entity
      if (callmove) callmove(x, y, 1);
    } else if (selectedEntity.type !== 'MOVE' && type === 'null') {
      // ask want buy&place
      // update
      // sent SelectEntity x y credit

      // sent pos
      const buyEvent: EventTypes.BuyEvent = {
        kind: parseInt(selectedEntity.type.substring(1)) - 1,
        pos: [x, y]
      };

      sendBuy(buyEvent);

      BodyStore.update((state) => {
        state.SelectEntity = resetToMoveMode;
      });

      BodyStore.update((state) => {
        state.pointer = [x, y];
      });

      console.log('new of type ' + selectedEntity.type);
    }
  };

  const computeRingSize = () => {
    if (selectedEntity.type === 'MOVE' && pointer[0] === x && pointer[1] === y) {
      if (type !== 'null') {
        // select
        return 'ring-8 ring-red-400';
      } else if (type === 'null' && tcommand.commandData.isSelected) {
        // place
        return 'ring-8 ring-green-400';
      }
    }
    return '';
  };

  const dataShow = () => {
    return (
      <>
        {type === 'null' ? (
          <></>
        ) : (
          <>
            <>{type + ' hp:' + hp} </>
            <>{'x:' + x + ' y:' + y}</>
            <>{'    a:' + action}</>
          </>
        )}
      </>
    );
  };

  return (
    <td
      className={`${computeRingSize()} eachcell cursor-pointer font `}
      draggable="false"
      onClick={cellOnClickHandler}>
      <div className="container">
        <span className="details  ">{dataShow()} </span>

        <img src={bg} className="image" />
        <img src={entityImg} className="overlay" />
      </div>
    </td>
  );
};

export default React.memo(Cell);
