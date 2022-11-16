import { OrderStatus } from './orderStatus.enum';
import { OrderType } from './orderType.enum';
import { ITrade } from './trade';

export interface ICryptoOrder {
  id: number;
  currencyPair: string;
  createdDateTime: Date;
  type: OrderType;
  price: number;
  quantity: number;
  filledQuantity: number;
  orderStatus: OrderStatus;
  trades: Array<ITrade>;
}

export const emptyCryptoOrder: ICryptoOrder = {
  id: 0,
  currencyPair: '',
  createdDateTime: new Date(),
  type: OrderType.BUY,
  price: 0,
  quantity: 0,
  filledQuantity: 0,
  orderStatus: OrderStatus.OPEN,
  trades: [],
};
