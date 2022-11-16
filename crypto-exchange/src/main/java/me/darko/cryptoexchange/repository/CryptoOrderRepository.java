package me.darko.cryptoexchange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.darko.cryptoexchange.model.CryptoOrder;

public interface CryptoOrderRepository extends JpaRepository<CryptoOrder, Long> {

	@Query(value = "SELECT o FROM CryptoOrder o " +
		"WHERE o.orderStatus like 'OPEN' and o.type like 'SELL' and o.price <= :price " +
		"ORDER BY o.price ASC, o.createdDateTime ASC")
	List<CryptoOrder> getSellLowerPricedOlderOrders(@Param("price") Double requiredPrice);

	@Query(value = "SELECT o FROM CryptoOrder o " +
		"WHERE o.orderStatus like 'OPEN' and o.type like 'BUY' and o.price >= :price " +
		"ORDER BY o.price DESC, o.createdDateTime ASC")
	List<CryptoOrder> getBuyHigherPricedOlderOrders(@Param("price") Double requiredPrice);
}
