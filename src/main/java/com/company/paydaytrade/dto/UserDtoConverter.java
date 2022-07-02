package com.company.paydaytrade.dto;

import com.company.paydaytrade.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    public UserDto converter(User u) {
        UserDto userDto = UserDto.builder()
                .name(u.getName())
                .Cash(u.getCash())
                .password(u.getPassword())
                .email(u.getEmail())
                .build();
        return userDto;
    }

}
