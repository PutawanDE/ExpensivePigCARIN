import anti1 from './assets/A1.gif';
import anti2 from './assets/A2.gif';
import anti3 from './assets/A3.gif';
import virus1 from './assets/V1.gif';
import virus2 from './assets/V2.gif';
import virus3 from './assets/V3.gif';
import cellnull from './assets/bk.png';

export type CellProps = {
  img: string;
  x: number;
  y: number;
  type: CellType;
  hp?: number;
  action?: string;
  shootDir?: string;
};

export enum CellType {
  A1 = 'A1',
  A2 = 'A2',
  A3 = 'A3',
  V1 = 'V1',
  V2 = 'V2',
  V3 = 'V3',
  empty = 'empty'
}

const dataAnti1: CellProps = {
  img: anti1,
  x: -1,
  y: -1,
  type: CellType.A1,
  hp: 0,
  action: 'none'
};

const dataAnti2: CellProps = {
  img: anti2,
  x: -1,
  y: -1,
  type: CellType.A2,
  hp: 0,
  action: 'none'
};
const dataAnti3: CellProps = {
  img: anti3,
  x: -1,
  y: -1,
  type: CellType.A3,
  hp: 0,
  action: 'none'
};

const dataVirus1: CellProps = {
  img: virus1,
  x: -1,
  y: -1,
  type: CellType.V1,
  hp: 0,
  action: 'none'
};
const dataVirus2: CellProps = {
  img: virus2,
  x: -1,
  y: -1,
  type: CellType.V2,
  hp: 0,
  action: 'none'
};

const dataVirus3: CellProps = {
  img: virus3,
  x: -1,
  y: -1,
  type: CellType.V3,
  hp: 0,
  action: 'none'
};

export const emptyCell: CellProps = {
  img: cellnull,
  x: -1,
  y: -1,
  type: CellType.empty
};

const allCell = {
  A1: dataAnti1,
  A2: dataAnti2,
  A3: dataAnti3,
  V1: dataVirus1,
  V2: dataVirus2,
  V3: dataVirus3,
  emptyCell: emptyCell
};

export const produceEntityCell = (
  type: string,
  x: number,
  y: number,
  action: string,
  hp:number
): CellProps => {
  const entitiy = { ...allCell[type as keyof typeof allCell] };
  entitiy.x = x;
  entitiy.y = y;

  if (type !== 'empty') {
    entitiy.action = action;
    entitiy.hp = hp;
  }

  return entitiy;
};

export const produceEmptyCell = (x: number, y: number): CellProps => {
  const cell = { ...allCell['emptyCell'] };
  cell.x = x;
  cell.y = y;
  return cell;
};
