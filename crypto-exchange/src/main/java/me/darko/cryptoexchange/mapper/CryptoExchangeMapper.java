package me.darko.cryptoexchange.mapper;

import org.mapstruct.Mapper;

import me.darko.cryptoexchange.dto.CryptoOrderDTO;
import me.darko.cryptoexchange.dto.NewOrderDTO;
import me.darko.cryptoexchange.dto.OrderBookDTO;
import me.darko.cryptoexchange.dto.TradeDTO;
import me.darko.cryptoexchange.model.CryptoOrder;
import me.darko.cryptoexchange.model.OrderBook;
import me.darko.cryptoexchange.model.Trade;

@Mapper
public interface CryptoExchangeMapper {
	CryptoOrderDTO cryptoOrderToDTO(CryptoOrder cryptoOrder);

	CryptoOrder DTOToCryptoOrder(CryptoOrderDTO cryptoOrderDTO);

	CryptoOrder NewOrderDTOToCryptoOrder(NewOrderDTO newOrderDTO);

	TradeDTO tradeToDTO(Trade trade);

	Trade DTOToTrade(TradeDTO tradeDTO);

	OrderBookDTO orderBookToDTO(OrderBook orderBook);

	OrderBook DTOToOrderBook(OrderBookDTO orderBookDTO);
}
