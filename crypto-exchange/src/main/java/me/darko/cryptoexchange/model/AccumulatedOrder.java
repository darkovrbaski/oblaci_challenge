package me.darko.cryptoexchange.model;

import lombok.Value;

@Value
public class AccumulatedOrder {
	Double price;
	Double quantity;
}
