package me.darko.cryptoexchange.model;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Embeddable
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccumulatedOrder {
	Double price;
	Double quantity;
}
