package com.company.paydaytrade.service;

import com.company.paydaytrade.entity.StockDto;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;

import java.io.IOException;
import java.util.List;
import java.util.Map;
@Service
public class YahooStockApiService {
    public StockDto getStock(String stockName) throws IOException {
        StockDto dto = null;
        Stock stock = YahooFinance.get(stockName);
        dto = new StockDto(stock.getName(), stock.getQuote().getPrice(), stock.getQuote().getChange(), stock.getCurrency(), stock.getQuote().getBid());
        return dto;
    }


    public Map<String, Stock> getStocks() throws IOException {
        String[] stockNames={"GOOG","AAPL","AAN","AAT","ABBV","ABC"};
        Map<String, Stock> stocks = YahooFinance.get(stockNames);

        return stocks;
    }

    public List<HistoricalQuote> getHistory(String stockName) throws IOException {
        Stock stock = YahooFinance.get(stockName);
        List<HistoricalQuote> history = stock.getHistory();
        return history;
    }




}
