package com.company.paydaytrade.service.impl;

import com.company.paydaytrade.dto.request.BuyUserStockRequest;
import com.company.paydaytrade.dto.request.SellUserStockRequest;
import com.company.paydaytrade.dto.UserStockDto;
import com.company.paydaytrade.dto.response.UserStockResponse;
import com.company.paydaytrade.entity.User;
import com.company.paydaytrade.entity.UserStock;
import com.company.paydaytrade.enums.ErrorCodeEnum;
import com.company.paydaytrade.exception.CustomNotFoundRestException;
import com.company.paydaytrade.repository.UserRepository;
import com.company.paydaytrade.repository.UserStocksRepository;
import com.company.paydaytrade.service.UserStockService;
import com.company.paydaytrade.util.UserStockDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserStockServiceImpl implements UserStockService {
    private final UserStocksRepository userStocksRepository;
    private final UserRepository userRepository;
    private final EmailSenderServiceImpl emailSenderServiceImpl;
    private final UserStockDtoConverter userStockDtoConverter;

    @Override
    public UserStockResponse getAllUserStocks() {
        List<UserStockDto> userStockDtoList = userStocksRepository.findAll()
                .stream()
                .map(userStockDtoConverter::converter)
                .collect(Collectors.toList());

        return UserStockResponse.builder()
                .userStockList(userStockDtoList)
                .build();
    }

    @Override
    public UserStock findUserStockByUserId(Integer id) {
        return userStocksRepository.findUserStocksByUserId(id)
                .orElseThrow(() -> new CustomNotFoundRestException(ErrorCodeEnum.USERSTOCK_NOT_FOUND));
    }

    @Override
    public UserStockDto buyStockTargetPrice(Integer id, BuyUserStockRequest buyUserStockRequest) throws IOException {
        Optional<User> user = userRepository.findUserById(id);
        Stock stock = YahooFinance.get(buyUserStockRequest.getStockName());
        BigDecimal stockPrice = stock.getQuote().getPrice();
        double stockPriceDouble = stockPrice.doubleValue();
        UserStock userStock = new UserStock();
        user.ifPresent(usr -> {
            if (usr.getCash() >= buyUserStockRequest.getTargetValue()) {
                Double userCash = usr.getCash();
                Double targetCash = buyUserStockRequest.getTargetValue();
                Double finalCash = userCash - targetCash;
                if (stockPriceDouble == buyUserStockRequest.getTargetValue()) {
                    usr.setCash(finalCash);
                    userStock.setUserId(usr.getId());
                    userStock.setStockName(buyUserStockRequest.getStockName());
                    userStock.setStockPrice(stockPriceDouble);
                    userRepository.save(usr);
                    userStocksRepository.save(userStock);
                    emailSenderServiceImpl.sendEmail(usr.getEmail(), "PaydayTrade", "The process of purchasing "
                            + buyUserStockRequest.getStockName() + " stock was successfully completed");
                }
            }
        });
        return userStockDtoConverter.converter(userStock);
    }

    @Override
    public UserStockDto sellTargetPrice(Integer id, SellUserStockRequest sellUserStockRequest) throws IOException {
        Optional<User> userOptional = userRepository.findUserById(id);
        Stock stock = YahooFinance.get(sellUserStockRequest.getStockName());
        BigDecimal stockPrice = stock.getQuote().getPrice();
        double stockPriceDouble = stockPrice.doubleValue();
        Optional<UserStock> userStocksOptional = userStocksRepository.findUserStocksByUserId(id);
        userOptional.ifPresent(user -> userStocksOptional.ifPresent(userStocks -> {
            if (userStocks.getStockName().equals(sellUserStockRequest.getStockName()) || userStocks.getStockPrice() >=
                    sellUserStockRequest.getTargetValue() || stockPriceDouble == sellUserStockRequest.getTargetValue()) {
                Double userCash = user.getCash();
                Double targetCash = sellUserStockRequest.getTargetValue();
                Double finalCash = userCash + targetCash;
                user.setCash(finalCash);
                userRepository.save(user);
                userStocks.setStockPrice(userStocks.getStockPrice() - sellUserStockRequest.getTargetValue());
                userStocksRepository.save(userStocks);
                emailSenderServiceImpl.sendEmail(user.getEmail(), "PaydayTrade", "The process of selling " +
                        sellUserStockRequest.getStockName() + " shares stock was successfully completed");
            }
        }));

        return userStocksOptional.map(userStockDtoConverter::converter).orElse(new UserStockDto());
    }

}
