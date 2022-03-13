import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import './App.css';
import StartMenu from './components/start/Start'
import SelectAntibody from './components/editAntibody/EditAntibody'
import SelectAntigen from './components/editAntigen/EditAntigen'
import AntigenResult from './components/result/AntigenResult'

function App() {

  return (
    // <Router>
    //   <Routes>
    //     <Route  path="/">
    //       <StartMenu />
    //     </Route>
    //     <Route  path="/editantigen">
    //       <StartMenu />
    //     </Route>
    //   </Routes>
    // </Router>

    <div>
      <Routes>
        <Route path="/" element = {<StartMenu/>}/>
        <Route path="/editantibody" element = {<SelectAntibody/>}/>
        <Route path="/editantigen" element = {<SelectAntigen/>}/>
        <Route path="/AntigenResult" element = {<AntigenResult/>}/>
      </Routes>
      
    </div>




  );
}

export default App;