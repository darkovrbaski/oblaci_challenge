package me.darko.cryptoexchange.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Value;
import me.darko.cryptoexchange.model.OrderStatus;
import me.darko.cryptoexchange.model.OrderType;

@Value
public class CryptoOrderDTO {
	Long id;
	String currencyPair;
	LocalDateTime createdDateTime;
	OrderType type;
	Double price;
	Double quantity;
	Double filledQuantity;
	OrderStatus orderStatus;
	List<TradeDTO> trades;
}
