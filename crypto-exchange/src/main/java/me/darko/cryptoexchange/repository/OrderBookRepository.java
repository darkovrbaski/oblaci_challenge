package me.darko.cryptoexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.darko.cryptoexchange.model.OrderBook;

public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {
}
