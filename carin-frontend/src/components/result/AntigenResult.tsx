import './Result.css';
import Trophy from "../../elements/result/trophy.gif";

function AntigenResult() {
    return (
        <div>
            <div className="pt-10">
                <img src={Trophy} className="trophy" />
            </div>

            <div className="textborder text-6xl font-bold py-10">
                THE WINNER IS COVID-19
            </div>

        </div>
    );
}

export default AntigenResult;