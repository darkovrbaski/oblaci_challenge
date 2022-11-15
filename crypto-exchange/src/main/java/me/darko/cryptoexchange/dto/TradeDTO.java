package me.darko.cryptoexchange.dto;

import java.time.LocalDateTime;

import lombok.Value;

@Value
public class TradeDTO {
	Long id;
	Long buyOrderId;
	Long sellOrderId;
	LocalDateTime timestamp;
	Double price;
	Double quantity;
}
