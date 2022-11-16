package me.darko.cryptoexchange.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@ElementCollection(targetClass = AccumulatedOrder.class)
	List<AccumulatedOrder> buyOrders;

	@ElementCollection(targetClass = AccumulatedOrder.class)
	List<AccumulatedOrder> sellOrders;
}
