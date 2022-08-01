package com.company.paydaytrade.util;

import com.company.paydaytrade.dto.UserDto;
import com.company.paydaytrade.dto.UserStockDto;
import com.company.paydaytrade.entity.User;
import com.company.paydaytrade.entity.UserStock;
import org.springframework.stereotype.Component;

@Component
public class UserStockDtoConverter {
    public  UserStockDto converter(UserStock userStocks) {
        return UserStockDto.builder()
                .stockName(userStocks.getStockName())
                .stockPrice(userStocks.getStockPrice())
                .userId(userStocks.getUserId())
                .build();
    }
}
