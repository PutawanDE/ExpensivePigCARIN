import { produceEmptyCell, produceEntityCell } from '../components/gameloop/CellFactory';
import { BodyStore } from '../components/gameloop/stores/BodyStore';
import { CreditStore } from '../components/gameloop/stores/CreditStore';
import { RemainStore } from '../components/gameloop/stores/RemainEntityStore';
import { EventTypes } from './EventTypes';

export const handleGameOutput = (output: EventTypes.OutputEvent): void => {
  const action = output.action;
  switch (action) {
    case 'move':
      const moveEvent = output as EventTypes.OutputMoveEvent;
      moveEntity(moveEvent.pos[0], moveEvent.pos[1], moveEvent.toPos[0], moveEvent.toPos[1]);
      break;
    case 'shoot':
      const ShootEvent = output as EventTypes.ShootEvent;
      shootEntity(ShootEvent.pos[0], ShootEvent.pos[1], ShootEvent.direction);
      break;
    case 'hp':
      const HpEvent = output as EventTypes.HpEvent;
      hpEntity(HpEvent.pos[0], HpEvent.pos[1], HpEvent.change);
      break;
    // case 'infect':
    case 'die':
      const DieEvent = output as EventTypes.DieEvent;
      killEntity(DieEvent.pos[0], DieEvent.pos[1]);
      break;
    case 'credit':
      const creditEvent = output as EventTypes.CreditEvent;
      credit(creditEvent.remain);
      break;
    case 'spawn':
      const spawnEvent = output as EventTypes.SpawnEvent;
      spawnNewEntity(spawnEvent.pos[0], spawnEvent.pos[1], spawnEvent.type);
      break;   
       case 'remain':
      const remainEvent = output as EventTypes.RemainEvent;
      remainEntity(remainEvent.remain[0],remainEvent.remain[1]);
      break;
      
  }
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

const shootEntity = (x: number, y: number, direction: string) => {
  BodyStore.update((state) => {
    state.Cell[y][x].action = "shoot";
    state.Cell[y][x].shootDir = direction;
  });
};

const hpEntity = (x: number, y: number, change: number) => {
  BodyStore.update((state) => {
    state.Cell[y][x].hp! += change;
  });
};

const killEntity = (x: number, y: number) => {
  console.log("clear");
  
  BodyStore.update((state) => {
    state.Cell[y][x] = produceEmptyCell(x, y);
  });
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


const remainEntity = (antiRemain: number, virusRemain: number) => {
  RemainStore.update((state) => {
    state.RemainData.antiRemain = antiRemain;
    state.RemainData.virusRemain = virusRemain;

  });
};





