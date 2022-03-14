import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import './App.css';
import StartMenu from './components/start/Start'
import ConfigSetup from './components/setupGame/editConfig/ConfigSetup'
import SelectAntibody from './components/setupGame/editGenetic/editAntibody/EditAntibody'
import SelectVirus from './components/setupGame/editGenetic/editVirus/EditVirus'
import InGame from './components/gameloop/GameLoop'
import ResultVirus from './components/result/AntigenResult'

function App() {

  return (
 
    <div>
      <Routes>
        <Route path="/" element = {<StartMenu/>}/>
        <Route path="/editConfig" element = {<ConfigSetup/>}/>
        <Route path="/editAnti" element = {<SelectAntibody/>}/>
        <Route path="/editVirus" element = {<SelectVirus/>}/>
        <Route path="/game" element = {<InGame/>}/>
        <Route path="/resultVirus" element = {<ResultVirus/>}/>
      </Routes>
      
    </div>




  );
}

export default App;