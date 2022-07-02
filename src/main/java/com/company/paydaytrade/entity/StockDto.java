package com.company.paydaytrade.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yahoofinance.Stock;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    private String name;
    private BigDecimal price;
    private BigDecimal change;
    private String currency;
    private BigDecimal bid;

    public StockDto(Stock stock) {
        this.name=stock.getName();
        this.price=stock.getQuote().getPrice();
        this.bid=stock.getQuote().getBid();
        this.currency=stock.getCurrency();
        this.change=stock.getQuote().getChange();
    }
}
