import { Link } from 'react-router-dom';
import { useState } from 'react';
import 'jqueryui';

import AntibodyCodePopup from './AntibodyCodePopup';

import A1config from '../../../../elements/editAntibody/A1config.gif';
import A2config from '../../../../elements/editAntibody/A2config.gif';
import A3config from '../../../../elements/editAntibody/A3config.gif';

import './EditAntibody.css';

//https://www.cluemediator.com/create-simple-popup-in-reactjs
//https://www.w3schools.com/react/react_forms.asp
function EditAntibody() {
  const [isOpen, setIsOpen] = useState(false);
  const [currentType, setCurrentType] = useState('');

  const togglePopup = (type: string) => {
    setCurrentType(type);
    setIsOpen(!isOpen);
  };

  return (
    <div className="background backgroundEdit">
      <div className="parent flex flex-col justify-center center">
        <div className="configBorder font-bold text-5xl py-6">EDIT ANTIBODY GENETIC CODE</div>
        <div className="px-32">
          <div className="flex flex-row justify-center child">
            <div className="pr-20">
              <button onClick={() => togglePopup('A1')}>
                <img src={A1config} className="selectModeSize " />
              </button>
            </div>
            <div className="pr-20">
              <button onClick={() => togglePopup('A2')}>
                <img src={A2config} className="selectModeSize " />
              </button>
            </div>
            <div className="">
              <button onClick={() => togglePopup('A3')}>
                <img src={A3config} className="selectModeSize" />
              </button>
            </div>
          </div>
        </div>
        <div className="py-4">
          <Link to="/SelectConfiguration">
            <button className="editbtn text-white font-bold py-4 px-10 rounded-full text-2xl">
              ✅ Done
            </button>
          </Link>
        </div>
      </div>

      {isOpen && (
        <AntibodyCodePopup
          name={`Antibody Genetic Code of ${currentType}`}
          type={currentType}
          handleClose={() => togglePopup('')}
        />
      )}
    </div>
  );
}

export default EditAntibody;
