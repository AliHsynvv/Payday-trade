package com.company.paydaytrade;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication()
@OpenAPIDefinition(
        info =
        @Info(
                title = "PaydayTrade Api",
                description = "Trading service",
                version = "v1"
        )
)
@EnableMethodSecurity
public class PaydayTradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaydayTradeApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

