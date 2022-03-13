import anti1 from './assets/A1.gif';
import anti2 from './assets/A2.gif';
import anti3 from './assets/A3.gif';
import virus1 from './assets/V1.gif';
import virus2 from './assets/V2.gif';
import virus3 from './assets/V3.gif';

import { CellProps } from './stores/BodyStore';

const dataAnti1: CellProps = {
  img: anti1,
  x: -1,
  y: -1,
  type: 'A1',
  hp: '100',
  action: 'none',
  toposX: -1,
  toposY: -1
};

const dataAnti2: CellProps = {
  img: anti2,
  x: -1,
  y: -1,
  type: 'A2',
  hp: '100',
  action: 'none',
  toposX: -1,
  toposY: -1
};
const dataAnti3: CellProps = {
  img: anti3,
  x: -1,
  y: -1,
  type: 'A3',
  hp: '100',
  action: 'none',
  toposX: -1,
  toposY: -1
};

const dataVirus1: CellProps = {
  img: virus1,
  x: -1,
  y: -1,
  type: 'V1',
  hp: '20',
  action: 'none',
  toposX: -1,
  toposY: -1
};
const dataVirus2: CellProps = {
  img: virus2,
  x: -1,
  y: -1,
  type: 'V2',
  hp: '30',
  action: 'none',
  toposX: -1,
  toposY: -1
};
const dataVirus3: CellProps = {
  img: virus3,
  x: -1,
  y: -1,
  type: 'V3',
  hp: '40',
  action: 'none',
  toposX: -1,
  toposY: -1
};

const allEntities = {
  A1: dataAnti1,
  A2: dataAnti2,
  A3: dataAnti3,
  V1: dataVirus1,
  V2: dataVirus2,
  V3: dataVirus3
};

const produceEntity = (type: string): CellProps => {
  return { ...allEntities[type as keyof typeof allEntities] };
};

export default produceEntity;
