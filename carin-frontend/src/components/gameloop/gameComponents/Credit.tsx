import { CreditStore } from "../stores/CreditStore"
import creditImage from "../assets/credit.png";
 

const Credit = () => {

  const credit = CreditStore.useState()
 
 

  return (
    <div className="coin">
          <img className="w-36 icon" src={creditImage }   />
          <p>{credit.creditData.credit}</p>
 
          
    </div>
  )
}

export default Credit
