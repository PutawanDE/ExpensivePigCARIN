import { Store } from 'pullstate';
import { produceEmptyCell } from '../CellFactory';

import { CellProps } from '../CellFactory';

type BodyStore = {
  Cell: CellProps[][];
  inputType: InputType;
  pointer: [number, number];
};

export enum InputType {
  A1 = 'A1',
  A2 = 'A2',
  A3 = 'A3',
  MOVE = 'MOVE'
}

export const populateEmptyCell = (row: number, column: number) => {
  const output: CellProps[][] = [];
  for (let i = 0; i < row; i++) {
    output[i] = [];
    for (let j = 0; j < column; j++) {
      output[i].push(produceEmptyCell(j, i));
    }
  }
  console.log(output);
  return output;
};

export const BodyStore = new Store<BodyStore>({
  Cell: populateEmptyCell(0, 0),
  inputType: InputType.MOVE,
  pointer: [-1, -1]
});
