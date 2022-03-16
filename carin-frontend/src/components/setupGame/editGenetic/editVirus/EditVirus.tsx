import { Link } from "react-router-dom";
import { useState } from "react";
import { SetupGame } from "../../setupGameSender"
import 'jqueryui';


import B1 from "../../../../elements/editVirus/B1.png";
import B2 from "../../../../elements/editVirus/B2.png";
import B3 from "../../../../elements/editVirus/B3.png";

import './EditVirus.css';

//https://www.cluemediator.com/create-simple-popup-in-reactjs
//https://www.w3schools.com/react/react_forms.asp
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
    setup.setupData.anti = genetic
  }

  const ConfigForm = (props: any) => {
    return (
      <div className="config-popup-box">
        <div className="config-text-box">
          <span className="close-icon" onClick={props.handleClose}></span>
          {props.content}
        </div>
      </div>
    );
  };



  return (
    <div className="background backgroundEdit">
      {/* <div className="container mx-auto py-10 text-center text-5xl font-bold">
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

            <div className="right pr-12 pt-3">
        <Link to="/editVirus">
          <button name={"editAntibody"} onClick={save} className="nextBTN"><img src={NextBTN} alt="" className="geneticbuttonpicture " /></button>
        </Link>
      </div> */}

      <div className="parent flex flex-col justify-center center">
        <div className="configBorder font-bold text-5xl py-6">
          EDIT VIRUS GENETIC CODE
        </div>
        <div className="px-40">
          <div className="flex flex-row justify-center child">
            <div className="pr-20">
              <button onClick={() => togglePopup()}>
                <img src={B1} className="selectModeSize " />
              </button>
            </div>
            <div className="pr-20">
              <button onClick={() => togglePopup()}>
                <img src={B2} className="selectModeSize " />
              </button>
            </div>
            <div className="">
              <button onClick={() => togglePopup()}>
                <img src={B3} className="selectModeSize" />
              </button>
            </div>

          </div>
        </div>
        <div className="py-4">
          <Link to="/SelectConfiguration">
            <button className="editbtn text-white font-bold py-4 px-10 rounded-full text-2xl">
              ✅Done
            </button>
          </Link>

        </div>
      </div>


      {
        isOpen && <ConfigForm content={
          <div className="config-container  flex h-screen w-screen bg-neutral-900/50 z-50  py-20 textboxBackground">
            <div className="">
              <div className="flex flex-col pt-5 pb-10 ">
                <div className="center text-2xl font-bold">
                  EDIT VIRUS GENETIC CODE
                </div>
                <form onSubmit={handleChange} className="center py-6">
                  <textarea
                    name={selectbox}
                    onChange={handleChange}
                    rows={15}
                    cols={80}
                    className="fixedtextarea focus:ring ring-pink-500 focus:border-rose-700 
              focus:outline-none px-4 py-4 border-dashed border-4 border-rose-500 rounded-3xl"
                  />
                </form>
                <div className="center">
                  <div className="flex flex-row justify-center">
                    <div className="px-2">

                      <button type="submit"
                        className="bg-green-500 hover:bg-green-700 text-white 
                font-bold py-2 px-8 rounded-full text-black text-lg"
                        onClick={handleSave}>
                        Save
                      </button>
                    </div>

                    <div className="px-2">
                      <button
                        className="bg-red-500 hover:bg-red-700 text-white 
                font-bold py-2 px-8 rounded-full text-black text-lg">
                        Reset
                      </button>
                    </div>
                  </div>
                </div>
              </div>

            </div>
          </div>
        }
          handleClose={togglePopup}
        />
      }


    </div>
  )
}

export default EditVirus;