package com.company.paydaytrade.dto.response;

import com.company.paydaytrade.dto.UserDto;
import com.company.paydaytrade.dto.UserStockDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStockResponse {
    public List<UserStockDto> userStockList;
}
