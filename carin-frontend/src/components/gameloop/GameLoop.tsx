import './GameLoop.css';
import BodyMap from './components/BodyMap';
import PanelEntity from './components/PanelEntity';
import Credit from './components/Credit';
 
import Utility from './components/Utility';
import { FpsView } from 'react-fps';

 

function GameLoop() {
  return (
    <div>
      <div className='GameLoop' >
      <FpsView width={240} height={180} left={1500} top={700}/>
              <div className="menubar">
                  <Credit />
                  <PanelEntity />
                  <Utility />
              </div>
        <BodyMap />
      </div>
    </div >
  );
}

export default GameLoop;
