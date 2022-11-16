package me.darko.cryptoexchange.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.Value;
import me.darko.cryptoexchange.model.OrderType;

@Value
public class NewOrderDTO {
	Long id;

	@Pattern(regexp = "^(BTCUSD)$", message = "invalid currency")
	String currencyPair;

	OrderType type;

	@Min(value = 0, message = "invalid price")
	Double price;

	@Min(value = 1, message = "invalid quantity")
	Double quantity;
}
