import { Store } from 'pullstate'
import { useState } from "react";


type StatusProps = {
    isGameEnd:boolean
    Tiile: string;
    Content: string;
 
}
const defaultStatus: StatusProps = {
    isGameEnd:false,
    Tiile: "",
    Content: "เว่้ยย"
}

type GameStatusStore = {
  GameStatusData: StatusProps;
}

 

export const GameStatus = new Store<GameStatusStore>({
    GameStatusData: defaultStatus,
})
