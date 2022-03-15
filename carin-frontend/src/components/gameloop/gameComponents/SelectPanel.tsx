import SelectInput from './SelectEntity';
import './Panel.css';
import grabicon from '../assets/grab.png';
import anti1 from '../assets/A1.gif';
import anti2 from '../assets/A2.gif';
import anti3 from '../assets/A3.gif';

import { InputType } from '../stores/BodyStore';

const SelectPanel = () => {
  return (
    <div className=" SelectEntity my-4 flex space-x-4">
      <div className=" panelEntity ">
        <SelectInput inputType={InputType.A1} img={anti1} />
        <SelectInput inputType={InputType.A2} img={anti2} />
        <SelectInput inputType={InputType.A3} img={anti3} />
      </div>
      <div className=" panelEntity ">
        <SelectInput inputType={InputType.MOVE} img={grabicon} />
      </div>
    </div>
  );
};

export default SelectPanel;
