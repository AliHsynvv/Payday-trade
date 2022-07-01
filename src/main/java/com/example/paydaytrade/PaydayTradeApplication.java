package com.example.paydaytrade;

import com.example.paydaytrade.dto.CreateUserRequest;
import com.example.paydaytrade.dto.UserDto;
import com.example.paydaytrade.dto.UserDtoConverter;
import com.example.paydaytrade.entity.User;
import com.example.paydaytrade.entity.UserStocks;
import com.example.paydaytrade.repository.UserRepository;
import com.example.paydaytrade.repository.UserStocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class PaydayTradeApplication {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserStocksRepository userStocksRepository;
    @Autowired
    UserDtoConverter userDtoConverter;

    public static void main(String[] args) {
        SpringApplication.run(PaydayTradeApplication.class, args);
    }


    @Bean
    public CommandLineRunner run() {
        CommandLineRunner clr = new CommandLineRunner() {
            @Override
            public void run(String[] args) {
            }
        };
        return clr;
    }
}
