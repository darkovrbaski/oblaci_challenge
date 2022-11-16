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