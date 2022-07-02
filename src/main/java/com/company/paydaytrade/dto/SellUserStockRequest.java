package com.company.paydaytrade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellUserStockRequest extends BaseUserStocksRequest {
    private Integer id;
    private Double targetValue;
}
