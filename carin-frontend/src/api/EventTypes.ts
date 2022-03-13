export namespace EventTypes {
  export type MoveEvent = {
    pos: Number[];
    toPos: Number[];
  };

  export type BuyEvent = {
    kind: Number;
    pos: Number[];
  };
}
