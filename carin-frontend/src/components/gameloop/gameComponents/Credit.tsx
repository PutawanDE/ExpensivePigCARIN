import { CreditStore } from "../stores/CreditStore"
import creditImage from "../assets/credit.png";
import "./Panel.css"


const Credit = () => {

  const credit = CreditStore.useState()



  return (
    <div className="pl-5">
      <div className="coinpanel py-10 px-32">
        <div className="cointext text-white text-3xl">
          {credit.creditData.credit}
        </div>
      </div>
    </div>

  )
}

export default Credit
