package com.company.paydaytrade.dto.response;

import com.company.paydaytrade.dto.UserDto;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    public List<UserDto> userList;
}
