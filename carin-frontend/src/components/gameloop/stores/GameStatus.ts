import { Store } from 'pullstate'
import { useState } from "react";


type StatusProps = {
    isGameEnd:boolean;
    isPlay:boolean;
    isShow:boolean;
    Tiile: string;
    Content: string;
 
}
export const defaultStatus: StatusProps = {
    isGameEnd:false,
    isPlay:false,
    isShow:false,
    Tiile: "",
    Content: ""
}

type GameStatusStore = {
  GameStatusData: StatusProps;
}

 

export const GameStatus = new Store<GameStatusStore>({
    GameStatusData: defaultStatus,
})
