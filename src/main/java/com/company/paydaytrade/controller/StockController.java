package com.company.paydaytrade.controller;

import com.company.paydaytrade.service.YahooStockApiService;
import com.company.paydaytrade.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/paydaytrade/stocks")
public class StockController {
    @Autowired
    YahooStockApiService yahooStockApiService;

    @GetMapping("/{stockName}")
    public ResponseEntity<ResponseDto> getStock(@PathVariable String stockName) throws IOException {
        return ResponseEntity.ok(ResponseDto.of(yahooStockApiService.getStock(stockName)));
    }

    @GetMapping("/history/{stockName}")
    public ResponseEntity<ResponseDto> getStockHistory(@PathVariable String stockName) throws IOException {
        return ResponseEntity.ok(ResponseDto.of(yahooStockApiService.getHistory(stockName)));
    }
}
