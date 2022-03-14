import { useState } from "react";
import ReactDOM from "react-dom";
import { Link } from "react-router-dom";
import './configCenter.css'


function ConfigSetup() {
  const [inputs, setInputs] = useState("");

  const handleChange = (event: any) => {
    const value = event.target.value;
    setInputs(event.target.value)
  }

  const handleSubmit = (event: any) => {
    event.preventDefault();
    console.log(inputs);
  }

  return (
    <>
      <div className="py-5">
        <div className="textboxBackground pt-5 pb-10">
          <form onSubmit={handleSubmit} className="center py-6">
            <textarea value={inputs} onChange={handleChange} rows={25} cols={120} className="fixedtextarea focus:ring ring-pink-500 focus:border-rose-700 focus:outline-none px-4 py-4 border-dashed border-4 border-rose-500 rounded-3xl" />
          </form>
          <div className="center">
            <div className="flex flex-row justify-center">
              <div className="px-2">
                <button className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-8 rounded-full text-black text-lg">
                  Submit
                </button>
              </div>

              <div className="px-2">
                <button className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-8 rounded-full text-black text-lg">
                  Cancel
                </button>
              </div>
            </div>


          </div>


        </div><input type="submit" />
      </div>


      <Link to="/editAnti">


        {/*// on Start setup sockjs session id */}

        <button className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-8 rounded-full text-black text-lg">
          Submit
        </button>
      </Link>
    </>

  )
}


export default ConfigSetup;
