package com.company.paydaytrade.service;

import com.company.paydaytrade.entity.StockDto;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import java.io.IOException;
import java.util.List;

@Service
public class YahooStockApiService {
    public StockDto getStock(String stockName) throws IOException {
        return new StockDto(YahooFinance.get(stockName));
    }

    public List<HistoricalQuote> getHistory(String stockName) throws IOException {
        return YahooFinance.get(stockName).getHistory();
    }
    //    public Map<String, Stock> getStocks() throws IOException {
//        String[] stockNames = {"GOOG", "AAPL", "AAN", "AAT", "ABBV", "ABC"};
//        Map<String, Stock> stocks = YahooFinance.get(stockNames);
//        return stocks;
//    }
}
