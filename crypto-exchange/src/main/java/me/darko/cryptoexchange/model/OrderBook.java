package me.darko.cryptoexchange.model;

import java.util.List;

import lombok.Value;

@Value
public class OrderBook {
	List<AccumulatedOrder> buyOrders;
	List<AccumulatedOrder> sellOrders;
}
