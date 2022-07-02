package com.company.paydaytrade.service;

import com.company.paydaytrade.dto.BuyUserStockRequest;
import com.company.paydaytrade.dto.SellUserStockRequest;
import com.company.paydaytrade.dto.UserStocksDto;
import com.company.paydaytrade.dto.UserStocksDtoConverter;
import com.company.paydaytrade.entity.User;
import com.company.paydaytrade.entity.UserStocks;
import com.company.paydaytrade.exception.UserStockNotFoundException;
import com.company.paydaytrade.repository.UserRepository;
import com.company.paydaytrade.repository.UserStocksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserStocksService {
    private final UserStocksRepository userStocksRepository;
    private final UserStocksDtoConverter userStocksDtoConverter;
    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;

    public List<UserStocksDto> getAllUserStocks() {
        List<UserStocks> userStocks = userStocksRepository.findAll();
        List<UserStocksDto> userStocksDtoList = new ArrayList<>();
        for (UserStocks us : userStocks) {
            userStocksDtoList.add(userStocksDtoConverter.converter(us));
        }
        return userStocksDtoList;
    }

    public UserStocks findUserStocksByUserId(Integer id) {
        return userStocksRepository.findUserStocksByUserId(id)
                .orElseThrow(() -> new UserStockNotFoundException("User stock not found"));
    }

    public UserStocksDto buyStockTargetPrice(Integer id, BuyUserStockRequest buyUserStockRequest) throws IOException {
        Optional<User> user = userRepository.findUserById(id);
        Stock stock = YahooFinance.get(buyUserStockRequest.getStockName());
        BigDecimal stockPrice = stock.getQuote().getPrice();
        double stockPriceDouble = stockPrice.doubleValue();
        UserStocks userStocks = new UserStocks();
        user.ifPresent(usr -> {
            if (usr.getCash() >= buyUserStockRequest.getTargetValue()) {
                Double userCash = usr.getCash();
                Double targetCash = buyUserStockRequest.getTargetValue();
                Double finalCash = userCash - targetCash;
                if (stockPriceDouble == buyUserStockRequest.getTargetValue()) {
                    usr.setCash(finalCash);
                    userStocks.setUserId(usr.getId());
                    userStocks.setStockName(buyUserStockRequest.getStockName());
                    userStocks.setStockPrice(stockPriceDouble);
                    userRepository.save(usr);
                    userStocksRepository.save(userStocks);
                    emailSenderService.sendEmail(usr.getEmail(), "PaydayTrade", "The process of purchasing "
                            + buyUserStockRequest.getStockName() + " stock was successfully completed");
                }
            }
        });
        return userStocksDtoConverter.converter(userStocks);
    }

    public UserStocksDto sellTargetPrice(Integer id, SellUserStockRequest sellUserStockRequest) throws IOException {
        Optional<User> userOptional = userRepository.findUserById(id);
        Stock stock = YahooFinance.get(sellUserStockRequest.getStockName());
        BigDecimal stockPrice = stock.getQuote().getPrice();
        double stockPriceDouble = stockPrice.doubleValue();
        Optional<UserStocks> userStocksOptional = userStocksRepository.findUserStocksByUserId(id);
        userOptional.ifPresent(user -> {
            userStocksOptional.ifPresent(userStocks -> {
                if (userStocks.getStockName().equals(sellUserStockRequest.getStockName()) || userStocks.getStockPrice() >= sellUserStockRequest.getTargetValue() || stockPrice.equals(sellUserStockRequest.getTargetValue())) {
                    Double userCash = user.getCash();
                    Double targetCash = sellUserStockRequest.getTargetValue();
                    Double finalCash = userCash + targetCash;
                    user.setCash(finalCash);
                    userRepository.save(user);
                    userStocks.setStockPrice(userStocks.getStockPrice() - sellUserStockRequest.getTargetValue());
                    userStocksRepository.save(userStocks);
                    emailSenderService.sendEmail(user.getEmail(), "PaydayTrade", "The process of selling " + sellUserStockRequest.getStockName() + " shares stock was successfully completed");
                }
            });
        });

        return userStocksOptional.map(UserStocksDtoConverter::converter).orElse(new UserStocksDto());
    }

}
