import { Store } from 'pullstate';
import { defaultGameSetup, GameSetup } from '../../api/GameAPI';

export const GameSetupStore = new Store<GameSetup>({
  antiGeneticCodes: defaultGameSetup.antiGeneticCodes,
  virusGeneticCodes: defaultGameSetup.virusGeneticCodes,
  gameConfig: defaultGameSetup.gameConfig
});
