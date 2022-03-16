import { Store } from 'pullstate'

type StatusProps = {
    isGameEnd:boolean;
    isPlay:boolean;
    isShow:boolean;
    status: string;
    virusDeadCount: number;
    antiDeadCount: number;
    timeUnitPlayed: number;
    isInMenu: boolean;
}
export const defaultStatus: StatusProps = {
    isGameEnd:false,
    isPlay:false,
    isShow:false,
    status: "",
    virusDeadCount: 0,
    antiDeadCount: 0,
    timeUnitPlayed:0,
    isInMenu: false
}

type GameStatusStore = {
  GameStatusData: StatusProps;
}

export const GameStatus = new Store<GameStatusStore>({
    GameStatusData: defaultStatus,
})
