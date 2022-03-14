import { Store } from 'pullstate'
 
type CreditProps = {
    credit: number;
 
}
const defaultCredit: CreditProps = {
  credit: 2000,
}

type CreditStore = {
  creditData: CreditProps;
}

 

export const CreditStore = new Store<CreditStore>({
    creditData: defaultCredit,
})
