import React, { Component } from 'react';

import './GameEnd.css';
 
const GameEnd = (props:any)=> {
  return (
    <div className="popup-end">
      <div className="endbox">
        {props.content}
      </div>
    </div>
  );
};
 
 
export default GameEnd;