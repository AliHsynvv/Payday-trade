package com.company.paydaytrade.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserStocksRequest extends BaseUserStocksRequest {
    private Integer id;
}
