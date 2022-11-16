package me.darko.cryptoexchange.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.darko.cryptoexchange.model.CryptoOrder;
import me.darko.cryptoexchange.model.OrderType;
import me.darko.cryptoexchange.model.Trade;
import me.darko.cryptoexchange.repository.TradeRepository;
import me.darko.cryptoexchange.service.TradeService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TradeServiceImpl implements TradeService {
	TradeRepository tradeRepository;

	@Override
	public Trade createTrade(final CryptoOrder newOrder, final CryptoOrder matchedOrder, final Double quantity) {
		final var trade = new Trade();
		trade.setId(0L);
		trade.setPrice(matchedOrder.getPrice());
		trade.setQuantity(quantity);
		trade.setTimestamp(LocalDateTime.now());
		if (newOrder.getType() == OrderType.BUY) {
			trade.setBuyOrderId(newOrder.getId());
			trade.setSellOrderId(matchedOrder.getId());
		} else {
			trade.setBuyOrderId(matchedOrder.getId());
			trade.setSellOrderId(newOrder.getId());
		}
		return tradeRepository.save(trade);
	}

	@Override
	public void deleteTrades() {
		tradeRepository.deleteAll();
	}
}
