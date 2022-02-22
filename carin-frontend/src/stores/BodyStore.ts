import { Store } from 'pullstate'
import cellnull from "../assets/bk.png";
 
type CellProps = {
  img: string;
  x: number;
  y: number ;
  type: string;
  hp: string;
  action: string;
  toposX: number ;
  toposY: number ;
}
const defaultCell: CellProps = {
   
  img: cellnull,
  x: 0,
  y: 0,
  type: "null",
  hp: "null",
  action: "null",
  toposX: 0,
  toposY: 0 
}

type BodyStore = {
  //we save painted color as hex code (string) in 2D array
  Cell: CellProps[][];
  SelectEntity: CellProps;
}


 const row  = 20;
 const colum  = 20;
//return an (16 x 16) 2D array filled with "#FFFFFF"
export const createCell = () => {
 
  const output: CellProps[][] = []
  for (let i = 0; i < row; i++) {
    output[i] = []
    for (let j = 0; j < colum; j++) {
      output[i].push(defaultCell);
    }
  }
  return output
}

export const BodyStore = new Store<BodyStore>({
  Cell: createCell(),
  SelectEntity: defaultCell,
})
