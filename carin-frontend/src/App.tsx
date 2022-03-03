import './App.css';
import Body from './components/Body';import PanelEntity from './components/PanelEntity';
import Credit from './components/Credit';
 
import Utility from './components/Utility';
import { FpsView } from 'react-fps';

 

function App() {

  return (
    <div>
      <div className='app' >
      <FpsView width={240} height={180} left={1500} top={700}/>
              <div className="header">
                  <Credit />
                  <PanelEntity />
                  <Utility />
                   
              </div>
        <Body />
      </div>
    </div >
  );
}

export default App;
