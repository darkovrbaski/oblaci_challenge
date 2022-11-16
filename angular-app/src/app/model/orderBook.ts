import { IAccumulatedOrder } from './AccumulatedOrder';

export interface IOrderBook {
  id: number;
  buyOrder: Array<IAccumulatedOrder>;
  sellOrder: Array<IAccumulatedOrder>;
}
