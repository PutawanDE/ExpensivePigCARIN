import produceEntity from '../components/gameloop/EntityFactory';
import { BodyStore, CellProps, defaultCell } from '../components/gameloop/stores/BodyStore';

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
    // case 'credit':
    case 'spawn':
      const spawnEvent = output as EventTypes.SpawnEvent;
      spawnNewEntity(spawnEvent.pos[0], spawnEvent.pos[1], spawnEvent.type);
      break;
  }
};

const spawnNewEntity = (x: number, y: number, type: string) => {
  const entity: CellProps = produceEntity(type);
  BodyStore.update((state) => {
    state.Cell[y][x] = entity;
    state.Cell[y][x].x = x;
    state.Cell[y][x].y = y;
  });
};

const moveEntity = (x: number, y: number, toX: number, toY: number) => {
  BodyStore.update((state) => {
    const entity: CellProps = { ...state.Cell[y][x] };
    state.Cell[y][x] = defaultCell;
    state.Cell[toY][toX] = entity;
  });
};
