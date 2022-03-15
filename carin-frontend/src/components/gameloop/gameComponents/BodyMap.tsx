import { EventTypes } from '../../../api/EventTypes';
import { sendMove } from '../../../api/GameAPI';

import { BodyStore } from '../stores/BodyStore';
import { RemainStore } from '../stores/RemainEntityStore';
import { defaultCommand } from '../eventCenter';

import { commandStore } from '../eventCenter';
import Cell from './Cell';
import './BodyMap.css';

import $ from 'jquery';
import 'jqueryui';
import { useCallback, useEffect } from 'react';

let zoomLv = 1;
const lowestZoomLv = 0.1;

const zoomIn = () => {
  zoomLv += 0.1;
  $('.target').css('transform', 'scale(' + zoomLv + ')');
};

const zoomOut = () => {
  const newZoomLv = zoomLv - 0.1;
  if(newZoomLv <= lowestZoomLv) return;

  zoomLv = newZoomLv;
  $('.target').css('transform', 'scale(' + zoomLv + ')');
};
const zoomReset = () => {
  zoomLv = 1;
  $('.target').css('transform', 'scale(' + zoomLv + ')');
};

const showDetails = () => {
  $('.details').css('transform', 'scale(' + 1 + ')');
};
const hideDetails = () => {
  $('.details').css('transform', 'scale(' + 0 + ')');
};

const Body = () => {
  useEffect(() => {
    $(function () {
      $('#draggable').draggable();
    });
  });

  const state = BodyStore.useState();
  const tcommand = commandStore.useState();
  const entitiyRemain = RemainStore.useState();
  const movefn = useCallback(
    (x: number, y: number, order: number) => {
      if (order === 0) {
        console.log('pick entity');
        tcommand.commandData.pos = [x, y];
        tcommand.commandData.isSelected = true;
      }
      if (order === 1 && tcommand.commandData.isSelected) {
        console.log('drop entity');
        console.log(tcommand.commandData);
        tcommand.commandData.topos = [x, y];
        tcommand.commandData.isPlaced = true;
      }

      if (tcommand.commandData.isSelected && tcommand.commandData.isPlaced) {
        console.log('action move');
        tcommand.commandData.action = 'move';
        ////////////  sent data
        console.log(tcommand.commandData);
        const pos = tcommand.commandData.pos;
        const toPos = tcommand.commandData.topos;

        if (toPos && pos) {
          const moveEvent: EventTypes.InputMoveEvent = { pos, toPos };
          sendMove(moveEvent);
        }

        /////////////////////
        tcommand.commandData = defaultCommand;
        tcommand.commandData.isSelected = false;
        tcommand.commandData.isPlaced = false;
      }
    },
    [tcommand]
  );

  return (
    <div className="BodyMap">
      <button className="btn zoom" onClick={zoomIn}>
        + <i className="fas fa-search-plus"></i>
      </button>
      <button className="btn zoom-out" onClick={zoomOut}>
        {' '}
        - <i className="fas fa-search-minus"></i>
      </button>
      <button className="btn zoom-init" onClick={zoomReset}>
        {' '}
        reset <i className="fas fa-recycle"></i>
      </button>

      <button className="btn show" onClick={showDetails}>
        show
      </button>
      <button className="btn hide" onClick={hideDetails}>
        hide <i className="fas fa-search-minus"></i>
      </button>

      <table className="ring-2 ring-gray-200 rounded-md mx-auto">
        <tbody className="box target" id="draggable">
          {state.Cell.map((row, i) => (
            <tr key={i}>
              {row.map((cell, j) => (
                <Cell key={`x-${j}-y-${i}`} cellProps={cell} callmove={movefn} />
              ))}
            </tr>
          ))}
        </tbody>
      </table>
      <>
        <div className="remain"> antiRemain {entitiyRemain.RemainData.antiRemain}</div>
        <div className="remain" > virusRemain {entitiyRemain.RemainData.virusRemain}</div>
      </>


    </div>
  );
};

export default Body;
