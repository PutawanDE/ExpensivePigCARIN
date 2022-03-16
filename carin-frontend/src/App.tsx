import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import './App.css';
import StartMenu from './components/startGame/Start'
import SelectAntibody from './components/setupGame/editGenetic/editAntibody/EditAntibody'
import SelectVirus from './components/setupGame/editGenetic/editVirus/EditVirus'
import InGame from './components/gameloop/GameLoop'
import SelectModes from './components/selectModes/SelectModes'
import GameSetupPage from './components/setupGame/editConfig/GameSetupPage'

function App() {

  return (
 
    <div>
      <Routes>
        <Route path="/" element = {<StartMenu/>}/>
        <Route path="/editAnti" element = {<SelectAntibody/>}/>
        <Route path="/editVirus" element = {<SelectVirus/>}/>
        <Route path="/game" element = {<InGame/>}/>
        <Route path="/SelectModes" element = {<SelectModes/>}/>
        <Route path="/gameSetup" element = {<GameSetupPage/>}/>
        
      </Routes>
      
    </div>




  );
}

export default App;