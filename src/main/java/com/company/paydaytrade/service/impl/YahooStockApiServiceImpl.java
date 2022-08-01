package com.company.paydaytrade.service.impl;

import com.company.paydaytrade.entity.StockDto;
import com.company.paydaytrade.service.YahooStockApi;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import java.io.IOException;
import java.util.List;

@Service
public class YahooStockApiServiceImpl implements YahooStockApi {
    public StockDto getStock(String stockName) throws IOException {
        return new StockDto(YahooFinance.get(stockName));
    }

    public List<HistoricalQuote> getHistory(String stockName) throws IOException {
        return YahooFinance.get(stockName).getHistory();
    }

}
