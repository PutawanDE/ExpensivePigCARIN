import { defaultGameSetup } from '../../../../api/GameAPI';
import ConfigPopup from '../../ConfigPopup';
import { GameSetupStore } from '../../GameSetupStore';

type AntibodyKey = {
  A1: string;
  A2: string;
  A3: string;
};

type AntibodyCodePopupProps = {
  name: string;
  type: string;
  handleClose: () => void;
};

function AntibodyCodePopup(props: AntibodyCodePopupProps) {
  const type = props.type;
  const code = GameSetupStore.useState(
    (s) => s.antiGeneticCodes[type as keyof AntibodyKey],
    [props.type]
  );

  const handleChange = (event: any) => {
    GameSetupStore.update((s) => {
      s.antiGeneticCodes[type as keyof AntibodyKey] = event.target.value;
    });
  };

  const handleReset = () => {
    GameSetupStore.update((s) => {
      s.antiGeneticCodes[type as keyof AntibodyKey] =
        defaultGameSetup.antiGeneticCodes[type as keyof AntibodyKey];
    });
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

export default AntibodyCodePopup;
