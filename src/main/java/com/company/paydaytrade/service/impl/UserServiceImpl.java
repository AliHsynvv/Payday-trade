package com.company.paydaytrade.service.impl;

import com.company.paydaytrade.dto.*;
import com.company.paydaytrade.dto.request.CreateUserRequest;
import com.company.paydaytrade.dto.request.DepositUserRequest;
import com.company.paydaytrade.dto.request.UpdateUserRequest;
import com.company.paydaytrade.dto.response.UserResponse;
import com.company.paydaytrade.entity.User;
import com.company.paydaytrade.enums.ErrorCodeEnum;
import com.company.paydaytrade.exception.CustomNotFoundRestException;
import com.company.paydaytrade.repository.UserRepository;
import com.company.paydaytrade.service.UserService;
import com.company.paydaytrade.util.UserDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse getAllUser() {
        List<UserDto> userDtoList = userRepository.findAll()
                .stream()
                .map(userDtoConverter::converter)
                .collect(Collectors.toList());

        return UserResponse.builder()
                .userList(userDtoList)
                .build();
    }

    @Override
    public UserDto createUser(CreateUserRequest createUserRequest) {
        User u = User.builder()
                .name(createUserRequest.getName())
                .email(createUserRequest.getEmail())
                .password(passwordEncoder.encode(createUserRequest.getPassword()))
                .cash(0D)
                .build();
        userRepository.save(u);
        return userDtoConverter.converter(u);
    }

    @Override
    public UserDto updateUser(Integer id, UpdateUserRequest updateUserRequest) {
        Optional<User> u = userRepository.findById(id);
        u.ifPresent(user -> {
            user.setEmail(updateUserRequest.getEmail());
            user.setName(updateUserRequest.getName());
            user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
            user.setCash(updateUserRequest.getCash());
            userRepository.save(user);
        });
        return u.map(userDtoConverter::converter).orElse(new UserDto());
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).map(userDtoConverter::converter)
                .orElseThrow(() -> new CustomNotFoundRestException(ErrorCodeEnum.USER_NOT_FOUND));
    }

    @Override
    public UserDto findUserById(Integer id) {
        return userRepository.findUserById(id).map(userDtoConverter::converter)
                .orElseThrow(() -> new CustomNotFoundRestException(ErrorCodeEnum.USER_NOT_FOUND));
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto loadCash(Integer id, DepositUserRequest depositUserRequest) {
        Optional<User> userOptional = userRepository.findUserById(id);
        userOptional.ifPresent(user -> {
            user.setCash(depositUserRequest.getCash());
            userRepository.save(user);
        });
        return userOptional.map(userDtoConverter::converter).orElse(new UserDto());
    }

}
