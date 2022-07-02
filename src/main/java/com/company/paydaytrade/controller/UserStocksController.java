package com.company.paydaytrade.controller;

import com.company.paydaytrade.dto.BuyUserStockRequest;
import com.company.paydaytrade.dto.ResponseDto;
import com.company.paydaytrade.dto.SellUserStockRequest;
import com.company.paydaytrade.service.UserStocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/paydaytrade/user/stocks")
public class UserStocksController {

    @Autowired
    UserStocksService userStocksService;

    @GetMapping
    public ResponseEntity<ResponseDto> getAllUserStocks() {
        return ResponseEntity.ok(ResponseDto.of(userStocksService.getAllUserStocks()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> findUserStockByUserId(@PathVariable Integer id) {
        return ResponseEntity.ok(ResponseDto.of(userStocksService.findUserStocksByUserId(id)));

    }

    @PutMapping("/buy/{id}")
    public ResponseEntity<ResponseDto> buyStockTargetPrice(@PathVariable Integer id,@RequestBody BuyUserStockRequest buyUserStockRequest) throws IOException {
        return ResponseEntity.ok(ResponseDto.of(userStocksService.buyStockTargetPrice(id, buyUserStockRequest)));
    }

    @PutMapping("/sell/{id}")
    public ResponseEntity<ResponseDto> sellStockTargetPrice(@PathVariable Integer id, @RequestBody SellUserStockRequest sellUserStockRequest) throws IOException {
        return ResponseEntity.ok(ResponseDto.of(userStocksService.sellTargetPrice(id, sellUserStockRequest)));
    }
}
