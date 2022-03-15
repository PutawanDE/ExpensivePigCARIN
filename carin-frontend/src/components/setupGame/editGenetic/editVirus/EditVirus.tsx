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
    </div>
  );
}

export default EditVirus;