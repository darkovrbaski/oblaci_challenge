package me.darko.cryptoexchange.service.impl;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.darko.cryptoexchange.dto.OrderBookDTO;
import me.darko.cryptoexchange.mapper.CryptoExchangeMapper;
import me.darko.cryptoexchange.model.OrderBook;
import me.darko.cryptoexchange.repository.CryptoOrderRepository;
import me.darko.cryptoexchange.service.OrderBookService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderBookServiceImpl implements OrderBookService {
	CryptoOrderRepository orderRepository;
	CryptoExchangeMapper mapper;

	@Override
	public OrderBookDTO getOrderBook() {
		return mapper.orderBookToDTO(new OrderBook(orderRepository.getOpenBuyOrders(), orderRepository.getOpenSellOrders()));
	}
}
