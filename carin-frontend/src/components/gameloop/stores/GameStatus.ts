import { Store } from 'pullstate'

type StatusProps = {
    isGameEnd:boolean;
    isShow:boolean;
    Tiile: string;
    Content: string;
 
}
const defaultStatus: StatusProps = {
    isGameEnd:true,
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
