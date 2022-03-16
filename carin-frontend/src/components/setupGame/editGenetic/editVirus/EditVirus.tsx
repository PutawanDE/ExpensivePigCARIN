import { Link } from 'react-router-dom';
import { useState } from 'react';
import 'jqueryui';
import VirusCodePopup from './VirusCodePopup';

import V1config from '../../../../elements/editVirus/V1config.png';
import V2config from '../../../../elements/editVirus/V2config.png';
import V3config from '../../../../elements/editVirus/V3config.png';

import '../GeneticForm.css';
import './EditVirus.css';

//https://www.cluemediator.com/create-simple-popup-in-reactjs
//https://www.w3schools.com/react/react_forms.asp
function EditVirus() {
  const [isOpen, setIsOpen] = useState(false);
  const [currentType, setCurrentType] = useState('');

  const togglePopup = (type: string) => {
    setCurrentType(type);
    setIsOpen(!isOpen);
  };

  return (
    <div className="background backgroundEdit">
      <div className="parent flex flex-col justify-center center">
        <div className="configBorder font-bold text-5xl py-6">EDIT VIRUS GENETIC CODE</div>
        <div className="px-40">
          <div className="flex flex-row justify-center child">
            <div className="pr-20">
              <button onClick={() => togglePopup('V1')}>
                <img src={V1config} className="selectModeSize " />
              </button>
            </div>
            <div className="pr-20">
              <button onClick={() => togglePopup('V2')}>
                <img src={V2config} className="selectModeSize " />
              </button>
            </div>
            <div className="">
              <button onClick={() => togglePopup('V3')}>
                <img src={V3config} className="selectModeSize" />
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

      {isOpen && (
        <VirusCodePopup
          name={`Virus Genetic Code of ${currentType}`}
          type={currentType}
          handleClose={() => togglePopup('')}
        />
      )}
    </div>
  );
}

export default EditVirus;
