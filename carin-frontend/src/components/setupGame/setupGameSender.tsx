import { Store } from 'pullstate'
 
type setupProps = {
    config: string;
    anti: {};
    virus?: {};
}
const defaultSetup: setupProps = {
    config: "",
    anti: {},
    virus: {},
 
}

type SetupGame = {
  //we save painted color as hex code (string) in 2D array
  setupData: setupProps;
}

//return an (16 x 16) 2D array filled with "#FFFFFF"
 
export const SetupGame = new Store<SetupGame>({
    setupData: defaultSetup,
})
