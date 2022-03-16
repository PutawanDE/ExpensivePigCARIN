import { BodyStore, InputType } from '../stores/BodyStore';
import './Panel.css'

type SelectButtonProps = {
  inputType: InputType;
  price:string;
  img: string;
  bgimg: string;
};

const SelectInput = ({ inputType,price, img ,bgimg }: SelectButtonProps) => {
  const state = BodyStore.useState((s) => s.inputType);

  const getRingColor = () => {
    if (state === inputType) {
      return 'outline-dashed outline-4 outline-green-600';
    }
    return '';
  };

  const select = () => {
    BodyStore.update((state) => {
      state.inputType = inputType;
      state.pointer = [-1, -1];
    });

    console.log('SelectInput');
    console.log(state);
  };

  const dataShow = () => {
    return <div className="text-xl font-bold">{price}</div>;
  };

  return (
    <div className="">
      <div
        className={`${getRingColor()} rounded-md cursor-pointer `}
        onClick={select}>
        <div>
          <div className="iconContainer px-1">
            <img className="selectIcon bgiconCentered" src={bgimg}/>
            <img className="Show selectIcon iconCentered" src={img} />
            <span className="">{dataShow()} </span>
          </div>
        </div>

      </div>
    </div>

  );
};

export default SelectInput;
