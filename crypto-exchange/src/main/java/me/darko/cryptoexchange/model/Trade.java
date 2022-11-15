package me.darko.cryptoexchange.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Trade {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "buyOrder_id")
	CryptoOrder buyOrder;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "sellOrder_id")
	CryptoOrder sellOrder;

	@Column
	LocalDateTime timestamp;

	@Column
	Double price;

	@Column
	Double quantity;
}
