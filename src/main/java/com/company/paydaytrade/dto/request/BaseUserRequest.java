package com.company.paydaytrade.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseUserRequest {
    private String name;
    private String password;
    private Double cash;
    private String email;
}
