package com.company.paydaytrade.controller;

import com.company.paydaytrade.service.YahooStockApiService;
import com.company.paydaytrade.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paydaytrade/stocks")
public class StockController {

    private final YahooStockApiService yahooStockApiService;

    @GetMapping("/{stockName}")
    public ResponseEntity<ResponseDto> getStock(@PathVariable String stockName) throws IOException {
        return ResponseEntity.ok(ResponseDto.of(yahooStockApiService.getStock(stockName)));
    }

    @GetMapping("/history")
    public ResponseEntity<ResponseDto> getStockHistory(@RequestParam String stockName) throws IOException {
        return ResponseEntity.ok(ResponseDto.of(yahooStockApiService.getHistory(stockName)));
    }
}
