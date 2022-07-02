package com.company.paydaytrade;

import com.company.paydaytrade.entity.User;
import com.company.paydaytrade.repository.UserRepository;
import com.company.paydaytrade.repository.UserStocksRepository;
import com.company.paydaytrade.dto.UserDtoConverter;
import com.company.paydaytrade.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;


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


    @Bean
    public CommandLineRunner run() {
        CommandLineRunner clr = new CommandLineRunner() {

            @Override
            public void run(String[] args) throws IOException {

            }
        };
        return clr;
    }
}
