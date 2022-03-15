import { produceEmptyCell, produceEntityCell } from '../components/gameloop/CellFactory';
import { BodyStore } from '../components/gameloop/stores/BodyStore';
import { CreditStore } from '../components/gameloop/stores/CreditStore';
import { EventTypes } from './EventTypes';

export const handleGameOutput = (output: EventTypes.OutputEvent): void => {
  const action = output.action;
  switch (action) {
    case 'move':
      const moveEvent = output as EventTypes.OutputMoveEvent;
      moveEntity(moveEvent.pos[0], moveEvent.pos[1], moveEvent.toPos[0], moveEvent.toPos[1]);
      break;
    // case 'shoot':
    // case 'hp':
    // case 'infect':
    // case 'die':
    case 'credit':
      const creditEvent = output as EventTypes.CreditEvent;
      credit(creditEvent.remain);
      break;
    case 'spawn':
      const spawnEvent = output as EventTypes.SpawnEvent;
      spawnNewEntity(spawnEvent.pos[0], spawnEvent.pos[1], spawnEvent.type);
      break;
  }
};
const credit = (remain: number) => {
  CreditStore.update((state) => {
    state.creditData.credit = remain;
  });
};

const spawnNewEntity = (x: number, y: number, type: string) => {
  const entity = produceEntityCell(type, x, y, 'spawn');
  BodyStore.update((state) => {
    state.Cell[y][x] = entity;
    state.Cell[y][x].x = x;
    state.Cell[y][x].y = y;
  });
};

const moveEntity = (x: number, y: number, toX: number, toY: number) => {
  BodyStore.update((state) => {
    const entity = { ...state.Cell[y][x] };
    state.Cell[y][x] = produceEmptyCell(x, y);
    state.Cell[toY][toX] = entity;
    state.Cell[toY][toX].x = toX;
    state.Cell[toY][toX].y = toY;
  });
};
