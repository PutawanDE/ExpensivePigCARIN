import { Store } from 'pullstate';
import { GameSetup } from '../../api/GameAPI';

export let defaultGameSetup: GameSetup = {
  antiGeneticCodes: {},
  virusGeneticCodes: {},
  gameConfig: ''
};

export const GameSetupStore = new Store<GameSetup>({
  antiGeneticCodes: defaultGameSetup.antiGeneticCodes,
  virusGeneticCodes: defaultGameSetup.virusGeneticCodes,
  gameConfig: defaultGameSetup.gameConfig
});
