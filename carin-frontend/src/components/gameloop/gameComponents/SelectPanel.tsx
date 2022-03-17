import SelectInput from './SelectInput';
import './Panel.css';
import grabicon from '../assets/grab.png';
import anti1 from '../assets/A1.gif';
import anti2 from '../assets/A2.gif';
import anti3 from '../assets/A3.gif';
import bgA1 from '../assets/storeBackgroundA1.png';
import bgA2 from '../assets/storeBackgroundA2.png';
import bgA3 from '../assets/storeBackgroundA3.png';

import { InputType } from '../stores/BodyStore';
import { CreditStore } from '../stores/CreditStore';

const SelectPanel = () => {
  const cost = CreditStore.useState((s) => s.creditData);

  return (
    <div className="">
      <div className="flex flex-row">
        <SelectInput inputType={InputType.A1} price={'C:' + cost.buyCost} img={anti1} bgimg={bgA1} />
        <SelectInput inputType={InputType.A2} price={'C:' + cost.buyCost} img={anti2} bgimg={bgA2} />
        <SelectInput inputType={InputType.A3} price={'C:' + cost.buyCost} img={anti3} bgimg={bgA3} />
        <div className="pl-10">
          <SelectInput
            inputType={InputType.MOVE}
            price={'MOVE -' + cost.moveHpCost + 'HP'}
            img={grabicon}
            bgimg={bgA3}
          />
        </div>

      </div>
    </div>
  );
};

export default SelectPanel;
