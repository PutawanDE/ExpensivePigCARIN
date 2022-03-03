import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import './App.css';
import StartMenu from './components/start/Start'
import SelectAntigen from './components/selectantigen/SelectAntigen'

function App() {

  return (
    <Router>
      <Routes>
        <Route  path="/">
          <StartMenu />
        </Route>
        <Route  path="/editantigen">
          <StartMenu />
        </Route>
      </Routes>
    </Router>



    // <SelectAntigen/>

  );
}

export default App;