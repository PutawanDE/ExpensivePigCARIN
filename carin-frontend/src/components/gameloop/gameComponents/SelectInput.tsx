import { BodyStore, InputType } from '../stores/BodyStore';
import './Panel.css'

type SelectButtonProps = {
  inputType: InputType;
  img: string;
  bgimg: string;
};

const SelectInput = ({ inputType, img ,bgimg }: SelectButtonProps) => {
  const state = BodyStore.useState((s) => s.inputType);

  const getRingColor = () => {
    if (state === inputType) {
      return 'ring-8 ring-green-400';
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
    return <>{inputType} </>;
  };

  return (
    <div className="">
      <div
        className={`${getRingColor()} rounded-md cursor-pointer `}
        onClick={select}>
        <div>
          <div className="iconContainer">
            <img className="selectIcon bgiconCentered" src={bgimg}/>
            <img className="Show selectIcon iconCentered" src={img} />
            <span className="textCentered">{dataShow()} </span>
          </div>
        </div>

      </div>
    </div>

  );
};

export default SelectInput;
