package me.darko.cryptoexchange.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.darko.cryptoexchange.dto.CryptoOrderDTO;
import me.darko.cryptoexchange.dto.NewOrderDTO;
import me.darko.cryptoexchange.mapper.CryptoExchangeMapper;
import me.darko.cryptoexchange.model.CryptoOrder;
import me.darko.cryptoexchange.model.OrderStatus;
import me.darko.cryptoexchange.model.OrderType;
import me.darko.cryptoexchange.repository.CryptoOrderRepository;
import me.darko.cryptoexchange.service.CryptoOrderService;
import me.darko.cryptoexchange.service.TradeService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CryptoOrderServiceImpl implements CryptoOrderService {
	CryptoOrderRepository orderRepository;
	TradeService tradeService;
	CryptoExchangeMapper mapper;

	static String CURRENCY_PAIR = "BTCUSD";

	@Override
	public CryptoOrderDTO processOrder(final NewOrderDTO orderDTO) {
		final var order = mapper.NewOrderDTOToCryptoOrder(orderDTO);
		order.setId(0L);
		order.setCurrencyPair(CURRENCY_PAIR);
		order.setCreatedDateTime(LocalDateTime.now());
		order.setFilledQuantity(0D);
		order.setOrderStatus(OrderStatus.OPEN);
		order.setTrades(new ArrayList<>());
		final var savedOrder = orderRepository.save(order);

		matchOrders(savedOrder);

		return mapper.cryptoOrderToDTO(savedOrder);
	}

	private void matchOrders(final CryptoOrder newOrder) {
		final List<CryptoOrder> matchedOrders;
		if (newOrder.getType() == OrderType.BUY) {
			matchedOrders = orderRepository.getSellLowerPricedOlderOrders(newOrder.getPrice());
		} else {
			matchedOrders = orderRepository.getBuyHigherPricedOlderOrders(newOrder.getPrice());
		}
		for (final var matchedOrder : matchedOrders) {
			if (newOrder.getOrderStatus() == OrderStatus.CLOSED) {
				break;
			}
			if (matchedOrder.getQuantity() == matchedOrder.getFilledQuantity() + (newOrder.getQuantity() - newOrder.getFilledQuantity())) {
				final var trade = tradeService.createTrade(newOrder, matchedOrder, newOrder.getQuantity() - newOrder.getFilledQuantity());

				matchedOrder.setClosedAndFulfilled();
				matchedOrder.addTrade(trade);

				newOrder.setClosedAndFulfilled();
				newOrder.addTrade(trade);

			} else if (matchedOrder.getQuantity() > matchedOrder.getFilledQuantity() + (newOrder.getQuantity() - newOrder.getFilledQuantity())) {
				final var trade = tradeService.createTrade(newOrder, matchedOrder, newOrder.getQuantity() - newOrder.getFilledQuantity());

				matchedOrder.setFilledQuantity(matchedOrder.getFilledQuantity() + (newOrder.getQuantity() - newOrder.getFilledQuantity()));
				matchedOrder.addTrade(trade);

				newOrder.setClosedAndFulfilled();
				newOrder.addTrade(trade);
			} else {
				final var trade = tradeService.createTrade(newOrder, matchedOrder, matchedOrder.getQuantity() - matchedOrder.getFilledQuantity());

				newOrder.setFilledQuantity(newOrder.getFilledQuantity() + (matchedOrder.getQuantity() - matchedOrder.getFilledQuantity()));
				newOrder.addTrade(trade);

				matchedOrder.setClosedAndFulfilled();
				matchedOrder.addTrade(trade);
			}
			orderRepository.save(matchedOrder);
			orderRepository.save(newOrder);
		}
	}

	@Override
	public CryptoOrderDTO getOrder(final Long orderId) {
		return mapper.cryptoOrderToDTO(orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new));
	}

	@Override
	public void deleteOrders() {
		orderRepository.deleteAll();
	}
}
