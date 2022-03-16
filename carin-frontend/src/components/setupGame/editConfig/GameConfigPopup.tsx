import { defaultGameSetup } from '../../../api/GameAPI';
import ConfigPopup from '../ConfigPopup';
import { GameSetupStore } from '../GameSetupStore';

function GameConfigPopup(props: any) {
  const config = GameSetupStore.useState((s) => s.gameConfig);

  const handleChange = (event: any) => {
    GameSetupStore.update((s) => {
      s.gameConfig = event.target.value;
    });
  };

  const handleReset = () => {
    GameSetupStore.update((s) => {
      s.gameConfig = defaultGameSetup.gameConfig;
    });
  };

  return (
    <ConfigPopup
      name={'Game Configuration'}
      value={config}
      handleChange={(e) => handleChange(e)}
      handleClose={props.handleClose}
      handleReset={handleReset}
    />
  );
}

export default GameConfigPopup;
