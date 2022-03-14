import { EventTypes } from '../../../api/EventTypes';
import { sendInput } from '../../../api/GameAPI';

import { BodyStore } from '../stores/BodyStore';
import { defaultCommand } from '../eventCenter';

import { commandStore } from '../eventCenter';
import Cell from './Cell';
import './BodyMap.css';

import $ from 'jquery';
import 'jqueryui';
import { useCallback } from 'react';

let zoomLv = 1;

const zoomIn = () => {
  zoomLv += 0.1;
  $('.target').css('transform', 'scale(' + zoomLv + ')');
};

const zoomOut = () => {
  zoomLv -= 0.1;
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

$(document).ready(function () {
  $(function () {
    $('#draggable').draggable();
  });
});

const Body = () => {
  const state = BodyStore.useState();
  const tcommand = commandStore.useState();
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
          sendInput(moveEvent);
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
      <a className="btn zoom" onClick={zoomIn}>
        {' '}
        + <i className="fas fa-search-plus"></i>
      </a>
      <a className="btn zoom-out" onClick={zoomOut}>
        {' '}
        - <i className="fas fa-search-minus"></i>
      </a>
      <a className="btn zoom-init" onClick={zoomReset}>
        {' '}
        reset <i className="fas fa-recycle"></i>
      </a>

      <a className="btn show" onClick={showDetails}>
        {' '}
        show <i className="fas fa-search-plus"></i>
      </a>
      <a className="btn hide" onClick={hideDetails}>
        {' '}
        hide <i className="fas fa-search-minus"></i>
      </a>

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
    </div>
  );
};

export default Body;
