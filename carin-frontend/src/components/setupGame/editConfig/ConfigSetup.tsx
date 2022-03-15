import { useState } from 'react';
import { Link } from 'react-router-dom';

import { SetupGame } from '../setupGameSender';

import './ConfigSetup.css';

function ConfigSetup() {
  const [config, setConfig] = useState('');
  const setup = SetupGame.useState();

  const handleChange = (event: any) => {
    const value = event.target.value;
    setConfig(value);
  };

  const handleSubmit = (event: any) => {
    setup.setupData.config = config;
    console.log(config);
  };



  return (
    <div className="parent flex flex-col justify-center center">
      <div className="py-5">
        <div className="textboxBackground pt-5 pb-10 ">
          <div className="center text-2xl font-bold">
            Game Configuration
          </div>
          <form onSubmit={handleSubmit} className="center py-6">
            <textarea
              value={config}
              onChange={handleChange}
              rows={15}
              cols={80}
              className="fixedtextarea focus:ring ring-pink-500 focus:border-rose-700 
              focus:outline-none px-4 py-4 border-dashed border-4 border-rose-500 rounded-3xl"
            />
          </form>
          <div className="center">
            <div className="flex flex-row justify-center">
              <div className="px-2">
                <Link to="/editAnti">
                  <button type="submit"
                    className="bg-green-500 hover:bg-green-700 text-white 
                font-bold py-2 px-8 rounded-full text-black text-lg"
                    onClick={handleSubmit}>
                    Submit
                  </button>
                </Link>
              </div>

              <div className="px-2">
                <button
                  className="bg-red-500 hover:bg-red-700 text-white 
                font-bold py-2 px-8 rounded-full text-black text-lg">
                  Clear
                </button>
              </div>
            </div>
          </div>
        </div>
        <input type="submit" />
      </div>

    </div>

















  );
}

export default ConfigSetup;
