import { EventTypes } from '../../../api/EventTypes';
import { sendMove } from '../../../api/GameAPI';

import { BodyStore } from '../stores/BodyStore';
import { RemainStore } from '../stores/RemainStore';
import { GameStatus } from '../stores/GameStatus';
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
  if (newZoomLv <= lowestZoomLv) return;

  zoomLv = newZoomLv;
  $('.target').css('transform', 'scale(' + zoomLv + ')');
};
const zoomReset = () => {
  zoomLv = 1;
  $('.target').css('transform', 'scale(' + zoomLv + ')');

  $("#draggable").css({
    'left': $("#draggable").data('originalLeft'),
    'top': $("#draggable").data('origionalTop')
  });

};

 

const showDetails = () => {
  // $('.details').css('transform', 'scale(' + 1 + ')');
  console.log("show");
  
  GameStatus.update((state) => {
    state.GameStatusData.isShow = true;
  });

 
};
const hideDetails = () => {
  console.log("hide");
  // $('.details').css('transform', 'scale(' + 0 + ')');
  GameStatus.update((state) => {
    state.GameStatusData.isShow = false;
  });

   
};

const Body = () => {
  useEffect(() => {
    $(function () {
      $('#draggable').draggable();
    });

    $("#draggable").data({
      'originalLeft': $("#draggable").css('left'),
      'origionalTop': $("#draggable").css('top')
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
    <div className="flex flex-row items-center justify-center pt-4">
      <div className="flex flex-col px-8">

        <div className="pb-4">
          <button className="hover:outline hover:outline-dashed outline-offset-2 outline-4 outline-red-800  shadow-sm bg-purple-700 hover:bg-purple-900  w-16 h-16 rounded-full font-bold text-center text-lg text-white" onClick={showDetails}>
            show
          </button>
        </div>


        <div className="pb-20">
          <button className="hover:outline hover:outline-dashed outline-offset-2 outline-4 outline-red-800  shadow-sm bg-purple-700 hover:bg-purple-900  w-16 h-16 rounded-full font-bold text-center text-lg text-white" onClick={hideDetails}>
            hide
          </button>
        </div>


        <div className="py-2">
          <button className="hover:outline hover:outline-dashed outline-offset-2 outline-4 outline-red-800 shadow-sm bg-purple-700 hover:bg-purple-900  w-16 h-16 rounded-full font-bold text-center text-lg text-white" onClick={zoomIn}>
            +
          </button>
        </div>




        <div className="py-2">
          <button className="hover:outline hover:outline-dashed outline-offset-2 outline-4 outline-red-800 shadow-sm bg-purple-700 hover:bg-purple-900  w-16 h-16 rounded-full font-bold text-center text-lg text-white" onClick={zoomOut}>
            -
          </button>
        </div>

        <div className="py-2">
          <button className="hover:outline hover:outline-dashed outline-offset-2 outline-4 outline-red-800 shadow-sm bg-purple-700 hover:bg-purple-900  w-16 h-16 rounded-full font-bold text-center text-lg text-white" onClick={zoomReset}>
            reset
          </button>
        </div>




      </div>

      <div className="py-2">
        <table className="bg-orange-100 outline-dashed outline-4 outline-offset-4 outline-pink-500 rounded-xl mx-auto cellBackgroundColor">
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

      <div className="py-2">
        <div className="pl-10 py-5">
          <div className="bg-white outline-dashed outline-4 outline-offset-4 outline-pink-500 rounded-xl text-3xl px-5 py-5 text-center font-bold"> Antibody: {entitiyRemain.RemainData.antiRemain}</div>
        </div>
        <div className="pl-10 py-5">
          <div className="bg-white outline-dashed outline-4 outline-offset-4 outline-pink-500 rounded-xl text-3xl px-5 py-5 text-center font-bold" > Virus: {entitiyRemain.RemainData.virusRemain}</div>
        </div>
      </div >


    </div>
  );
};

export default Body;
