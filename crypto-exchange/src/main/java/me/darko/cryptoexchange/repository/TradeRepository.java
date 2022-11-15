package me.darko.cryptoexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.darko.cryptoexchange.model.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
