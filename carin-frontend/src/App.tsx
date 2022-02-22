import './App.css';
import Body from './components/Body';
import PanelEntity from './components/PanelEntity';
import Credit from './components/Credit';
 
import Utility from './components/Utility';
import { FpsView } from 'react-fps';

 

function App() {

  return (
    <div>
      <div className='app' >
      <FpsView width={240} height={180} left={90} top={90}/>
              <div className="header">
                  <Utility />
                  <PanelEntity />
                  <Credit />
                  
                   
              </div>
        <Body />
      </div>
    </div >
  );
}

export default App;
