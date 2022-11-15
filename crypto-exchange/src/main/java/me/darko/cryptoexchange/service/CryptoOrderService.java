package me.darko.cryptoexchange.service;

import me.darko.cryptoexchange.dto.CryptoOrderDTO;
import me.darko.cryptoexchange.dto.NewOrderDTO;

public interface CryptoOrderService {
	CryptoOrderDTO processOrder(NewOrderDTO orderDTO);

	CryptoOrderDTO getOrder(Long orderId);

	void deleteOrders();
}
