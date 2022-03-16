import { Store } from 'pullstate'
 
type RemainProps = {
    antiRemain: number;
    virusRemain: number;
 
}
export const defaultRemain: RemainProps = {
    antiRemain: 0,
    virusRemain: 0
}

type RemainStore = {
  RemainData: RemainProps;
}

 

export const RemainStore = new Store<RemainStore>({
    RemainData: defaultRemain,
})
