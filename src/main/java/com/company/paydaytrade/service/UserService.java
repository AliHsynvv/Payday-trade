package com.company.paydaytrade.service;

import com.company.paydaytrade.dto.request.CreateUserRequest;
import com.company.paydaytrade.dto.request.DepositUserRequest;
import com.company.paydaytrade.dto.request.UpdateUserRequest;
import com.company.paydaytrade.dto.UserDto;
import com.company.paydaytrade.dto.response.UserResponse;

public interface UserService {
    UserResponse getAllUser();

    UserDto createUser(CreateUserRequest createUserRequest);

    UserDto updateUser(Integer id, UpdateUserRequest updateUserRequest);

    UserDto findUserByEmail(String email);

    UserDto findUserById(Integer id);

    void deleteById(Integer id);

    UserDto loadCash(Integer id, DepositUserRequest depositUserRequest);
}
