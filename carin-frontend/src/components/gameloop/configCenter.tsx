 import { useState } from "react";
import ReactDOM from "react-dom";
import { Link } from "react-router-dom";
import StartBtn from "../../elements/start/StartBtn.gif";
 

function ConfigSetup() {
  const [inputs , setInputs] = useState("");
 
  const handleChange = (event:any) => {
    const value = event.target.value;
    setInputs(event.target.value)
  }

  const handleSubmit = (event:any) => {
    event.preventDefault();
    console.log(inputs);
  }

  return (
    <>
    
    <form onSubmit={handleSubmit}>

<textarea   value={inputs}   onChange={handleChange}      
     rows={20}
    cols={20}/>
  <input type="submit" />
</form>
          <Link to="/editAnti">


          {/*// on Start setup sockjs session id */}

          <button className="content-center startbutton">
              <img src={StartBtn} alt="my image" className="startbuttonpicture" />
          </button>
      </Link>
    </>
   
  )
}

 
export default ConfigSetup;
 