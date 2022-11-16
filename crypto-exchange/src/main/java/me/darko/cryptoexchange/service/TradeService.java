package me.darko.cryptoexchange.service;

import me.darko.cryptoexchange.model.CryptoOrder;
import me.darko.cryptoexchange.model.Trade;

public interface TradeService {

	Trade createTrade(CryptoOrder newOrder, CryptoOrder matchedOrder);

	void deleteTrades();
}
