import { OrderType } from './orderType.enum';

export interface INewOrder {
  id: number;
  currencyPair: string;
  type: OrderType;
  price: number;
  quantity: number;
}
