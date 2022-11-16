import { IAccumulatedOrder } from './accumulatedOrder';

export interface IOrderBook {
  buyOrders: Array<IAccumulatedOrder>;
  sellOrders: Array<IAccumulatedOrder>;
}

export const emptyOrderBook: IOrderBook = {
  buyOrders: [],
  sellOrders: [],
};
