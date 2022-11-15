package me.darko.cryptoexchange.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CryptoOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	List<Trade> trades;
}
