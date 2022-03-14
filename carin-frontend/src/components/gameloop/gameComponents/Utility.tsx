
import pause from "../assets/pause.png";
import speed from "../assets/speed.png";
import exit from "../assets/exit.png";
import { Link } from "react-router-dom";
const Utility = () => {

    const sentPasue = () => {
        console.log("pasue");
    }
    const sentSpeed = () => {
        console.log("speed");
    }
    const sentExit = () => {
        console.log("exit");
    }
 

  return (
    <div className="flex  space-x-3">
          <img className="w-36 icon" src={pause} onClick={sentPasue} />
          <img className="w-36 icon" src={speed} onClick={sentSpeed} />
          <Link to="/">
          <img className="w-36 icon" src={exit} onClick={sentExit} />
          </Link>
          
    </div>
  )
}

export default Utility