import { Component, OnInit } from '@angular/core';
import { INewOrder, emptyNewOrder } from 'src/app/model/newOrder';
import { IOrderBook, emptyOrderBook } from 'src/app/model/orderBook';
import { CryptoExchangeService } from 'src/app/services/crypto-exchange.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css'],
})
export class HomepageComponent implements OnInit {
  orderBook: IOrderBook = emptyOrderBook;
  newOrder: INewOrder = emptyNewOrder;

  constructor(private cryptoExchangeService: CryptoExchangeService) {}

  ngOnInit(): void {
    this.getOrderBook();
  }

  getOrderBook() {
    this.cryptoExchangeService
      .getOrderBook()
      .subscribe((data) => (this.orderBook = data));
  }

  placeOrder() {
    this.cryptoExchangeService
      .processOrder(this.newOrder)
      .subscribe((data) => this.getOrderBook());
  }
}
