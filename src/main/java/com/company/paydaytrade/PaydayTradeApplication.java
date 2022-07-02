package com.company.paydaytrade;

import com.company.paydaytrade.repository.UserRepository;
import com.company.paydaytrade.repository.UserStocksRepository;
import com.company.paydaytrade.dto.UserDtoConverter;
import com.company.paydaytrade.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PaydayTradeApplication {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserStocksRepository userStocksRepository;
    @Autowired
    UserDtoConverter userDtoConverter;
    @Autowired
    EmailSenderService emailSenderService;


    public static void main(String[] args) {
        SpringApplication.run(PaydayTradeApplication.class, args);
    }

}
