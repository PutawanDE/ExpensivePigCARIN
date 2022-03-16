export namespace EventTypes {
  export type InputMoveEvent = {
    pos: number[];
    toPos: number[];
  };

  export type BuyEvent = {
    kind: Number;
    pos: number[];
  };

  export interface OutputEvent {
    action: string;
  }

  export interface OutputMoveEvent extends OutputEvent {
    pos: number[];
    toPos: number[];
  }

  export interface ShootEvent extends OutputEvent {
    pos: number[];
    direction: string;
  }

  export interface HpEvent extends OutputEvent {
    pos: number[];
    change: number;
  }

  export interface InfectEvent extends OutputEvent {
    pos: number[];
    type: string;
  }

  export interface DieEvent extends OutputEvent {
    pos: number[];
    action: string;
  }

  export interface CreditEvent extends OutputEvent {
    remain: number;
  }

  export interface SpawnEvent extends OutputEvent {
    pos: number[];
    type: string;
    hp: number;
  }
  export interface RemainEvent extends OutputEvent {
    remain: number[];
  }
  export interface GameEndEvent extends OutputEvent {
    status: string;
    virusDeadCount: number;
    antiDeadCount: number;
    timeUnitPlayed: number;
  }

  export interface RestartEvent extends OutputEvent {
    status: string;
    msg: string;
  }
}
