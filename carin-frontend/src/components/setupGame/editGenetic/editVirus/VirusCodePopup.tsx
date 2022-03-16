import ConfigPopup from '../../ConfigPopup';
import { defaultGameSetup, GameSetupStore } from '../../GameSetupStore';

type VirusKey = {
  V1: string;
  V2: string;
  V3: string;
};

type VirusCodePopupProps = {
  name: string;
  type: string;
  handleClose: () => void;
};

function VirusCodePopup(props: VirusCodePopupProps) {
  const type = props.type;
  const code = GameSetupStore.useState(
    (s) => s.virusGeneticCodes[type as keyof VirusKey],
    [props.type]
  );

  const handleChange = (event: any) => {
    GameSetupStore.update((s) => {
      s.virusGeneticCodes[type as keyof VirusKey] = event.target.value;
    });
  };

  const handleReset = () => {
    if (window.confirm('Do you want to reset to default? Your changes will be lost.')) {
      GameSetupStore.update((s) => {
        s.virusGeneticCodes[type as keyof VirusKey] =
          defaultGameSetup.virusGeneticCodes[type as keyof VirusKey];
      });
    }
  };

  return (
    <ConfigPopup
      name={props.name}
      value={code ? code : ''}
      handleChange={(e) => handleChange(e)}
      handleClose={props.handleClose}
      handleReset={handleReset}
    />
  );
}

export default VirusCodePopup;
