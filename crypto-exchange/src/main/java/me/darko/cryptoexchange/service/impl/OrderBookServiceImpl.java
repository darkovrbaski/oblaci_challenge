package me.darko.cryptoexchange.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.darko.cryptoexchange.dto.OrderBookDTO;
import me.darko.cryptoexchange.mapper.CryptoExchangeMapper;
import me.darko.cryptoexchange.repository.OrderBookRepository;
import me.darko.cryptoexchange.service.OrderBookService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderBookServiceImpl implements OrderBookService {
	OrderBookRepository orderBookRepository;
	CryptoExchangeMapper mapper;

	@Override
	public OrderBookDTO getOrderBook() {
		return mapper.orderBookToDTO(orderBookRepository.findById(1L).orElseThrow(EntityNotFoundException::new));
	}
}
