import { OrderType } from './orderType.enum';

export interface INewOrder {
  id: number;
  currencyPair: string;
  type: OrderType;
  price: number;
  quantity: number;
}

export const emptyNewOrder: INewOrder = {
  id: 0,
  currencyPair: 'BTCUSD',
  type: OrderType.BUY,
  price: 0,
  quantity: 0,
};
