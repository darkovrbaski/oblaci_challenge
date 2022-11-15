package me.darko.cryptoexchange.dto;

import lombok.Value;
import me.darko.cryptoexchange.model.OrderType;

@Value
public class NewOrderDTO {
	Long id;
	String currencyPair;
	OrderType type;
	Double price;
	Double quantity;
}
