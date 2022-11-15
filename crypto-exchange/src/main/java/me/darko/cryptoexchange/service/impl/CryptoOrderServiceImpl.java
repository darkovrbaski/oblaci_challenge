package me.darko.cryptoexchange.service.impl;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.darko.cryptoexchange.dto.CryptoOrderDTO;
import me.darko.cryptoexchange.dto.NewOrderDTO;
import me.darko.cryptoexchange.service.CryptoOrderService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CryptoOrderServiceImpl implements CryptoOrderService {
	@Override
	public CryptoOrderDTO processOrder(final NewOrderDTO orderDTO) {
		return null;
	}

	@Override
	public CryptoOrderDTO getOrder(final Long orderId) {
		return null;
	}

	@Override
	public void deleteOrders() {

	}
}
