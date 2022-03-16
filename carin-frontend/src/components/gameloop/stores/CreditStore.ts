import { Store } from 'pullstate';

type CreditProps = {
  credit: number;
  buyCost: number;
  moveHpCost: number;
};
const defaultCredit: CreditProps = {
  credit: 0,
  buyCost: 0,
  moveHpCost: 0
};

type CreditStore = {
  creditData: CreditProps;
};

export const CreditStore = new Store<CreditStore>({
  creditData: defaultCredit
});
