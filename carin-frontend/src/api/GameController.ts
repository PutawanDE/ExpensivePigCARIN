import { produceEmptyCell, produceEntityCell } from '../components/gameloop/CellFactory';
import { BodyStore, InputType, populateEmptyCell } from '../components/gameloop/stores/BodyStore';
import { CreditStore } from '../components/gameloop/stores/CreditStore';
import { defaultRemain, RemainStore } from '../components/gameloop/stores/RemainStore';
import { defaultStatus, GameStatus } from '../components/gameloop/stores/GameStatus';

import { EventTypes } from './EventTypes';
import { resetSpeed } from '../components/gameloop/gameComponents/Utility';

const audio = document.getElementById("myAudio") as any;

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
    case 'infect':
      const infectEvent = output as EventTypes.InfectEvent;
      infect(infectEvent.pos[0], infectEvent.pos[1], infectEvent.type);
      break;
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
      spawnNewEntity(spawnEvent.pos[0], spawnEvent.pos[1], spawnEvent.type, spawnEvent.hp);
      break;
    case 'remain':
      const remainEvent = output as EventTypes.RemainEvent;
      remainEntity(remainEvent.remain[0], remainEvent.remain[1]);
      break;
    case 'gameover':
      const gameEndEvent = output as EventTypes.GameEndEvent;
      gameOver(gameEndEvent);
      break;
    case 'restart':
      const restartEvent = output as EventTypes.RestartEvent;
      restart(restartEvent);
      break;
  }
};

const moveEntity = (x: number, y: number, toX: number, toY: number) => {
  BodyStore.update((state) => {
    const entity = { ...state.Cell[y][x] };
    state.Cell[y][x] = produceEmptyCell(x, y);
    state.Cell[toY][toX] = entity;
    state.Cell[toY][toX].action = 'move';
    state.Cell[toY][toX].x = toX;
    state.Cell[toY][toX].y = toY;
  });
};

const shootEntity = (x: number, y: number, direction: string) => {
  audio?.play();
  BodyStore.update((state) => {
    const action = state.Cell[y][x].action;
    if (action === 'shoot0') {
      state.Cell[y][x].action = 'shoot1';
    } else {
      state.Cell[y][x].action = 'shoot0';
    }

    state.Cell[y][x].shootDir = direction;
  });
};

const hpEntity = (x: number, y: number, change: number) => {
  BodyStore.update((state) => {
    if (change < 0) {
      state.Cell[y][x].action = 'reducehp';
    } else {
      state.Cell[y][x].action = 'increasehp';
    }

    state.Cell[y][x].hp! += change;
  });
};

const killEntity = (x: number, y: number) => {
  console.log('clear');

  BodyStore.update((state) => {
    state.Cell[y][x] = produceEmptyCell(x, y);
  });
};

const infect = (x: number, y: number, type: string) => {
  BodyStore.update((state) => {
    state.Cell[y][x].action = 'infect';
  });
};

const credit = (remain: number) => {
  CreditStore.update((state) => {
    state.creditData.credit = remain;
  });
};

const spawnNewEntity = (x: number, y: number, type: string, hp: number) => {
  const entity = produceEntityCell(type, x, y, 'spawn', hp);
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

const gameOver = (event: EventTypes.GameEndEvent) => {
  const { status, virusDeadCount, antiDeadCount, timeUnitPlayed } = event;
  GameStatus.update((state) => {
    state.GameStatusData.isGameEnd = true;
    state.GameStatusData.status = status;
    state.GameStatusData.virusDeadCount = virusDeadCount;
    state.GameStatusData.antiDeadCount = antiDeadCount;
    state.GameStatusData.timeUnitPlayed = timeUnitPlayed;
  });
};

const restart = (event: EventTypes.RestartEvent) => {
  const status = event.status;
  const msg = event.msg;

  if (status === 'SUCCESS') {
    console.log('Restarting... Setting up new Game....');
    BodyStore.update((s) => {
      s.Cell = populateEmptyCell(s.m, s.n);
      s.inputType = InputType.MOVE;
      s.pointer = [-1, -1];
    });

    CreditStore.update((s) => {
      s.creditData.credit = s.creditData.initialCredit;
    });

    GameStatus.update((s) => {
      s.GameStatusData = defaultStatus;
    });

    RemainStore.update((s) => {
      s.RemainData = defaultRemain;
    });

    resetSpeed();
  } else if (status === 'FAIL') {
    if (window.confirm(`Error restarting this game. ${msg}. You will need to create a new Game.`)) {
      window.location.replace('/');
    }
  }
};
