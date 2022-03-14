import { Store } from 'pullstate';

export type CommandProps = {
  pos: [number, number];
  isSelected: boolean;
  action: string;
  topos?: [number, number];
  isPlaced?: boolean;
  change?: string;
  kind?: string;
};
export const defaultCommand: CommandProps = {
  pos: [-10, -10],
  isSelected: false,
  action: 'null',
  topos: [-20, -20],
  isPlaced: false
};

type commandStore = {
  //we save painted color as hex code (string) in 2D array
  commandData: CommandProps;
};

export const commandStore = new Store<commandStore>({
  commandData: defaultCommand
});
