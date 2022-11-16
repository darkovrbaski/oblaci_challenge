package me.darko.cryptoexchange.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import me.darko.cryptoexchange.dto.CryptoOrderDTO;
import me.darko.cryptoexchange.dto.NewOrderDTO;
import me.darko.cryptoexchange.dto.OrderBookDTO;
import me.darko.cryptoexchange.service.CryptoOrderService;
import me.darko.cryptoexchange.service.OrderBookService;
import me.darko.cryptoexchange.service.TradeService;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CryptoExchangeController {
	CryptoOrderService orderService;
	OrderBookService orderBookService;
	TradeService tradeService;

	@PostMapping("/order")
	public ResponseEntity<CryptoOrderDTO> processOrder(@Valid @RequestBody final NewOrderDTO orderDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.processOrder(orderDTO));
	}

	@GetMapping("/orderbook")
	public ResponseEntity<OrderBookDTO> getOrderBook() {
		return ResponseEntity.status(HttpStatus.OK).body(orderBookService.getOrderBook());
	}

	@GetMapping("/order/{id}")
	public ResponseEntity<CryptoOrderDTO> getOrder(@RequestParam("id") final Long orderId) {
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrder(orderId));
	}

	@DeleteMapping("/order/all")
	public ResponseEntity<?> deleteOrders() {
		orderService.deleteOrders();
		tradeService.deleteTrades();
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
