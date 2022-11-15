package me.darko.cryptoexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.darko.cryptoexchange.model.CryptoOrder;

public interface CryptoOrderRepository extends JpaRepository<CryptoOrder, Long> {
}
