package com.company.paydaytrade.dto.request;

import com.company.paydaytrade.dto.UserDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest extends UserDto {
    private Integer id;
}
