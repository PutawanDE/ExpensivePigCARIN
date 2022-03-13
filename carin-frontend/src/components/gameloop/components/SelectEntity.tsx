import { BodyStore } from "../stores/BodyStore"


type CellProps = {
  img: string;
  x: number;
  y: number;
  type: string;
  hp: string;
  action: string;
  toposX: number;
  toposY: number;
}

type SelectColorButtonProps = {
  entitydata: CellProps;
  img: string;
  type: string;
}


const SelectEntity = ({entitydata, type ,img }: SelectColorButtonProps) => {




  //modify this function to highlight correctly
  const state = BodyStore.useState()

  const computeRingSize = () => {
    if ( entitydata.img === state.SelectEntity.img ) {
      return "ring-8 ring-green-400"
    }
    return ""
  }



  const selectedEntity = () => {
    BodyStore.update(state => { state.SelectEntity =  entitydata})
    console.log("SelectEntity");
    console.log(state.SelectEntity);
  }

  const dataShow = () => {
    return < >{type} </> 
       
  }

  return (

    <div className={`${computeRingSize()}   rounded-md border-black border-2 SelectEntity cursor-pointer  `}
      onClick={selectedEntity}
    >
      <img  className="Show" src={img} />
      <span className="selinfo">{dataShow()} </span>
    </div>
  )
}

export default SelectEntity
