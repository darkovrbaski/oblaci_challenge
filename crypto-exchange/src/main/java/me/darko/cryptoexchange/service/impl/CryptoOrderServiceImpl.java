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
import me.darko.cryptoexchange.model.Trade;
import me.darko.cryptoexchange.repository.CryptoOrderRepository;
import me.darko.cryptoexchange.repository.TradeRepository;
import me.darko.cryptoexchange.service.CryptoOrderService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CryptoOrderServiceImpl implements CryptoOrderService {
	CryptoOrderRepository orderRepository;
	TradeRepository tradeRepository;
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

		matchOrders(order);

		return mapper.cryptoOrderToDTO(orderRepository.save(order));
	}

	private void matchOrders(final CryptoOrder order) {
		final List<CryptoOrder> orders;
		if (order.getType() == OrderType.BUY) {
			orders = orderRepository.filterOpenAndPriceLETRequiredOrders(order.getPrice());
		} else {
			orders = orderRepository.filterOpenAndPriceGETRequiredOrders(order.getPrice());
		}
		for (final var o : orders) {
			if (order.getOrderStatus() == OrderStatus.OPEN) {
				//case 1
				if (o.getQuantity() == o.getFilledQuantity() + (order.getQuantity() - order.getFilledQuantity())) {
					o.setOrderStatus(OrderStatus.CLOSED);
					o.setFilledQuantity(o.getQuantity());
					orderRepository.save(o);

					order.setOrderStatus(OrderStatus.CLOSED);
					order.setFilledQuantity(order.getQuantity());
					break;
				}
				//case 2
				if (o.getQuantity() > o.getFilledQuantity() + (order.getQuantity() - order.getFilledQuantity())) {
					o.setFilledQuantity(o.getFilledQuantity() + (order.getQuantity() - order.getFilledQuantity()));
					orderRepository.save(o);

					order.setOrderStatus(OrderStatus.CLOSED);
					order.setFilledQuantity(order.getQuantity());
					break;
				}
				//case 3
				if (o.getQuantity() < o.getFilledQuantity() + (order.getQuantity() - order.getFilledQuantity())) {
					order.setFilledQuantity(order.getFilledQuantity() + (o.getQuantity() - o.getFilledQuantity()));

					o.setOrderStatus(OrderStatus.CLOSED);
					o.setFilledQuantity(o.getQuantity());
					orderRepository.save(o);
				}
			}
		}
	}

	private void createTrade(final CryptoOrder order, final CryptoOrder o) {
		final var trade = new Trade();
		trade.setId(0L);
		trade.setPrice(o.getPrice());
		trade.setQuantity(order.getQuantity() - order.getFilledQuantity());
		trade.setTimestamp(LocalDateTime.now());
		if (order.getType() == OrderType.BUY) {
			trade.setBuyOrder(order);
			trade.setSellOrder(o);
		} else {
			trade.setBuyOrder(o);
			trade.setSellOrder(order);
		}
		tradeRepository.save(trade);
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
