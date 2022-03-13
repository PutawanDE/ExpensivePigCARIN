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
    toPos: number[];
  }

  export interface HpEvent extends OutputEvent {
    pos: number[];
    change: number;
  }

  export interface InfectEvent extends OutputEvent {
    pos: number[];
    kind: number;
  }

  export interface DieEvent extends OutputEvent {
    action: string;
    pos: number[];
  }

  export interface CreditEvent extends OutputEvent {
    change: number;
  }

  export interface SpawnEvent extends OutputEvent {
    pos: number[];
    type: string;
  }
}
