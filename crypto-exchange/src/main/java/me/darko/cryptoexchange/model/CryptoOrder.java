package me.darko.cryptoexchange.model;

import static javax.persistence.FetchType.EAGER;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CryptoOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column
	String currencyPair;

	@Column
	LocalDateTime createdDateTime;

	@Column
	@Enumerated(EnumType.STRING)
	OrderType type;

	@Column
	Double price;

	@Column
	Double quantity;

	@Column
	Double filledQuantity;

	@Column
	@Enumerated(EnumType.STRING)
	OrderStatus orderStatus;

	@ManyToMany(fetch = EAGER)
	List<Trade> trades;

	public void setClosedAndFulfilled() {
		orderStatus = OrderStatus.CLOSED;
		filledQuantity = quantity;
	}

	public void addTrade(final Trade trade) {
		trades.add(trade);
	}
}
