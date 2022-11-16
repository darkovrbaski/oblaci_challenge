export interface ITrade {
  id: number;
  buyOrderId: number;
  sellOrderId: number;
  timestamp: Date;
  price: number;
  quantity: number;
}
