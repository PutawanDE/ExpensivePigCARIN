import { Store } from 'pullstate'
 
type CommandProps = {
    pos: [number, number];
    pos_use: boolean;
    action: string;
    topos?: [number, number];
    topos_use?: boolean;
    change?: string;
    kind?: string;
}
const defaultCommand: CommandProps = {
    pos: [-10, -10],
    pos_use: false,
    action: "null",
    topos: [-20, -20],
    topos_use: false,
}

type commandStore = {
  //we save painted color as hex code (string) in 2D array
  commandData: CommandProps;
}

 
//return an (16 x 16) 2D array filled with "#FFFFFF"
 

export const commandStore = new Store<commandStore>({
    commandData: defaultCommand,
})
