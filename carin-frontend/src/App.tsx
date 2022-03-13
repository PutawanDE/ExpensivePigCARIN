import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import './App.css';
import StartMenu from './components/start/Start'
import ConfigSetup from './components/gameloop/configCenter'
import SelectAntibody from './components/config/editAntibody/EditAntibody'
import SelectVirus from './components/config/editVirus/EditVirus'
import InGame from './components/gameloop/GameLoop'

function App() {

  return (
 
    <div>
      <Routes>
        <Route path="/" element = {<StartMenu/>}/>
        <Route path="/editConfig" element = {<ConfigSetup/>}/>
        <Route path="/editAnti" element = {<SelectAntibody/>}/>
        <Route path="/editVirus" element = {<SelectVirus/>}/>
        <Route path="/game" element = {<InGame/>}/>
      </Routes>
    </div>




  );
}

export default App;