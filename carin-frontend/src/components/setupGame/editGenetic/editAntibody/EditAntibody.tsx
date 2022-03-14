import { Link } from "react-router-dom";
import { useState } from "react";
import { SetupGame } from "../../setupGameSender"
import 'jqueryui';
 
import GeneticForm from '../GeneticForm';

import AGA from "../../assets/AGA.gif";
import AGB from "../../assets/AGB.gif";
import AGC from "../../assets/AGC.gif";
import NextBTN from "../../assets/NextBTN.png";

import '../GeneticForm.css';
import './EditAntibody.css';

//https://www.cluemediator.com/create-simple-popup-in-reactjs
//https://www.w3schools.com/react/react_forms.asp
function EditAntibody() {
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
    setup.setupData.anti = genetic
  }



  return (
    <div className="background">
      <div className="container mx-auto py-10 text-center text-5xl font-bold">
        EDIT ANTIBODY GENETIC CODE
      </div>
      <div className="flex flex-row ">
        <div className="basis-1/3 px-20">

          <button className="center" onClick={() => { togglePopup(); setIsselectbox("A1"); }}  ><img src={AGA} alt="" className="geneticbuttonpicture " /></button>
        </div>
        <div className="basis-1/3 px-20">
          <button className="center" onClick={() => { togglePopup(); setIsselectbox("A2"); }}  ><img src={AGB} alt="" className="geneticbuttonpicture " /></button>
        </div>
        <div className="basis-1/3 px-20">
          <button className="center" onClick={() => { togglePopup(); setIsselectbox("A3"); }} ><img src={AGC} alt="" className="geneticbuttonpicture " /></button>
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
        <Link to="/editVirus">

          {/* save anti-genetic string[]*/}

          <button   name={"editAntibody"} onClick={save }   className="nextBTN"><img src={NextBTN} alt="" className="geneticbuttonpicture " /></button>
        </Link>
      </div>

    </div>
  )
}

export default EditAntibody;