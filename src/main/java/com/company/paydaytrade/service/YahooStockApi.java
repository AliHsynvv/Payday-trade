package com.company.paydaytrade.service;

import com.company.paydaytrade.entity.StockDto;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.util.List;

public interface YahooStockApi {
    StockDto getStock(String stockName) throws IOException;

    List<HistoricalQuote> getHistory(String stockName) throws IOException;
}
