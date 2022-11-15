package me.darko.cryptoexchange.dto;

import java.util.List;

import lombok.Value;
import me.darko.cryptoexchange.model.AccumulatedOrder;

@Value
public class OrderBookDTO {
	List<AccumulatedOrder> buyOrders;
	List<AccumulatedOrder> sellOrders;
}
