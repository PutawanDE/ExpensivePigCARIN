import './Result.css';
import Trophy from "../../elements/result/trophy.gif";

function AntigenResult() {
    return (
        <div className="resultbackground">
            <div className="pt-10">
                <img src={Trophy} className="trophy" />
            </div>
            <div className="textborder-pink text-5xl font-bold py-10">
                THE WINNER IS COVID-19
            </div>
            <button className="center bg-blue-500 hover:bg-blue-700 text-white font-bold py-6 px-10 hover:px-9 rounded-full">
                Play Again
            </button>


        </div>
    );
}

export default AntigenResult;