package com.company.paydaytrade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStockDto {
    private String stockName;
    private Double stockPrice;
    private Integer userId;
}
