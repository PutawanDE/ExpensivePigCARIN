import {  BodyStore } from "../stores/BodyStore"
import Cell from "./Cell"
import '../css/Body.css';

import   $ from 'jquery';
import 'jqueryui';


var zoomLv = 1;

const zoomIn = () => {
    zoomLv += 0.1;
    $('.target').css('transform', 'scale(' + zoomLv + ')');

}

const zoomOut = () => {
    zoomLv -= 0.1;
    $('.target').css('transform', 'scale(' + zoomLv + ')');
 
}
const zoomReset = () => {
     zoomLv = 1;
    $('.target').css('transform', 'scale(' + zoomLv + ')');
}
 
    $( function() {
         $( "#draggable" ).draggable();
      } );
 
      const showDetails = () => {

        $('.details').css('transform', 'scale(' + 1 + ')');
    
      }
      const hideDetails = () => {
        $('.details').css('transform', 'scale(' + 0 + ')');
      }
 

const Body = () => {
    const state = BodyStore.useState()

    return (
        <div className="body" >

            <a className="btn zoom" onClick={zoomIn}> + <i className="fas fa-search-plus"></i></a>
            <a className="btn zoom-out" onClick={zoomOut}> - <i className="fas fa-search-minus"></i></a>
            <a className="btn zoom-init" onClick={zoomReset}> reset <i className="fas fa-recycle"></i></a>
            
            <a className="btn show" onClick={showDetails}> show <i className="fas fa-search-plus"></i></a>
            <a className="btn hide" onClick={hideDetails}> hide <i className="fas fa-search-minus"></i></a>

            <table className="ring-2 ring-gray-200 rounded-md mx-auto" >

                <tbody className="box target" id="draggable"   >

                    {state.Cell.map((row, i) =>
                        <tr key={i}  >
                            {row.map((cell, j) =>
                                <Cell x={j} y={i} type={""} hp={state.Cell[j][i].hp} action={""} toposX={0} toposY={0}    />)}
                        </tr>
                    )}

                </tbody>


            </table>
        </div>



    )
}

export default Body
