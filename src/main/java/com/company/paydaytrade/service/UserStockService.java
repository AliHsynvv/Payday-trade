package com.company.paydaytrade.service;

import com.company.paydaytrade.dto.request.BuyUserStockRequest;
import com.company.paydaytrade.dto.request.SellUserStockRequest;
import com.company.paydaytrade.dto.UserStockDto;
import com.company.paydaytrade.dto.response.UserStockResponse;
import com.company.paydaytrade.entity.UserStock;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface UserStockService {
    UserStockResponse getAllUserStocks();

    UserStock findUserStockByUserId(Integer id);

    UserStockDto buyStockTargetPrice(Integer id, BuyUserStockRequest buyUserStockRequest) throws IOException;

    UserStockDto sellTargetPrice(Integer id, SellUserStockRequest sellUserStockRequest) throws IOException;
}
