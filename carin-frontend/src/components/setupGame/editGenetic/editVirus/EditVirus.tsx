import { GameSetup, startGame } from '../../../../api/GameAPI';

import { Link } from "react-router-dom";
import { useState } from "react";
import 'jqueryui';

import GeneticForm from '../GeneticForm';
import { SetupGame } from "../../setupGameSender"


import V1 from "../../assets/AGA.gif";
import V2 from "../../assets/AGB.gif";
import V3 from "../../assets/AGC.gif";
import NextBTN from "../../assets/NextBTN.png";

import '../GeneticForm.css';
import './EditVirus.css';
function EditVirus() {
  const setup = SetupGame.useState()

  const [genetic, setGenetic] = useState({});

  const handleChange = (event: any) => {
    const name = event.target.name;
    console.log(name);

    const value = event.target.value;
    setGenetic(values => ({ ...values, [name]: value }))
  }
  const handleSave = (event: any) => {
    setIsOpen(false);
    event.preventDefault();
    console.log(genetic);
  }

  const [selectbox, setIsselectbox] = useState("");
  const [isOpen, setIsOpen] = useState(false);
  const togglePopup = () => {
    setIsOpen(!isOpen);
  }
  const save = () => {
    setup.setupData.virus = genetic
    console.log(setup.setupData)
  }

  const sentSetup_Start = () : void => {

    setup.setupData.virus = genetic  
    console.log(genetic)
    console.log(setup.setupData)
    
    const Setup : GameSetup = {
      antiGeneticCodes: ["", "", ""],
      virusGeneticCodes: ["", "", ""],
      gameConfig: ""
    };
    // var antiGeneticCodes:string = Object.values(setup.setupData)[1] as  string
    // var virusGeneticCodes:string = Object.values(setup.setupData)[2] as  string


    // const testSetup : GameSetup = {
    //   antiGeneticCodes: Object.values(antiGeneticCodes),
    //   virusGeneticCodes: Object.values(virusGeneticCodes),
    //   gameConfig: Object.values(setup.setupData)[0] as string
    // };
    // console.log(testSetup)
 
    startGame(Setup);
  }  


  return (
    <div className="background">
      <div className="container mx-auto py-10 text-center text-5xl font-bold">
              EDIT ANTIGEN GENETIC CODE
      </div>
      <div className="flex flex-row ">
        <div className="basis-1/3 px-20">
          <button className="center" onClick={() => { togglePopup(); setIsselectbox("V1"); }}><img src={V1} alt="" className="geneticbuttonpicture " /></button>
        </div>
        <div className="basis-1/3 px-20">
          <button className="center" onClick={() => { togglePopup(); setIsselectbox("V2"); }}><img src={V2} alt="" className="geneticbuttonpicture " /></button>
        </div>
        <div className="basis-1/3 px-20">
          <button className="center" onClick={() => { togglePopup(); setIsselectbox("V3"); }}><img src={V3} alt="" className="geneticbuttonpicture " /></button>
        </div>
      </div>

      {isOpen && <GeneticForm content={<>
        <textarea
          name={selectbox}
          onChange={handleChange}
          className='geneticbox'
          rows={20}
          cols={150} />
        <button onClick={handleSave} >Test button</button>
      </>}
        handleClose={togglePopup}
      />}



      <div className="right pr-12 pt-3">
        <Link to="/game">
          
            {/* save virus-genetic string[]
              then send anti&virus genetic 
            */}

        <button className="nextBTN" onClick={sentSetup_Start} ><img src={NextBTN} alt="" className="geneticbuttonpicture " /></button>
        </Link>
      </div>
    </div>
  );
}

export default EditVirus;