import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ICryptoOrder } from '../model/cryptoOrder';
import { IOrderBook } from '../model/orderBook';

@Injectable({
  providedIn: 'root',
})
export class CryptoExchangeService {
  private cryptoExchangeUrl = `${environment.apiUrl}`;

  constructor(private http: HttpClient) {}

  processOrder(order: ICryptoOrder): Observable<ICryptoOrder> {
    return this.http
      .post<ICryptoOrder>(`${this.cryptoExchangeUrl}/order`, order)
      .pipe(catchError(this.handleError));
  }

  getOrderBook(): Observable<IOrderBook> {
    return this.http
      .get<IOrderBook>(`${this.cryptoExchangeUrl}/orderbook`)
      .pipe(catchError(this.handleError));
  }

  getOrder(orderId: number): Observable<ICryptoOrder> {
    return this.http
      .get<ICryptoOrder>(`${this.cryptoExchangeUrl}/order/${orderId}`)
      .pipe(catchError(this.handleError));
  }

  deleteAll(): Observable<any> {
    return this.http
      .delete<any>(`${this.cryptoExchangeUrl}/order/all`)
      .pipe(catchError(this.handleError));
  }

  handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(() => {
      return errorMessage;
    });
  }
}
