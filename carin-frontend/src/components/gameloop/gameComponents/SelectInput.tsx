import { BodyStore, InputType } from '../stores/BodyStore';

type SelectButtonProps = {
  inputType: InputType;
  img: string;
};

const SelectInput = ({ inputType, img }: SelectButtonProps) => {
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
      state.pointer = [-1, -1] ;
    });

    console.log('SelectInput');
    console.log(state);
  };

  const dataShow = () => {
    return <>{inputType} </>;
  };

  return (
    <div
      className={`${getRingColor()}   rounded-md border-black border-2 SelectEntity cursor-pointer  `}
      onClick={select}>
      <img className="Show" src={img} />
      <span className="selinfo">{dataShow()} </span>
    </div>
  );
};

export default SelectInput;
