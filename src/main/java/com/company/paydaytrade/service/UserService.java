package com.company.paydaytrade.service;

import com.company.paydaytrade.dto.*;
import com.company.paydaytrade.entity.User;
import com.company.paydaytrade.exception.UserNotFoundException;
import com.company.paydaytrade.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<UserDto> getAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(userDtoConverter.converter(user));
        }
        return userDtoList;
    }

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

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    public User findUserById(Integer id) {
        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public UserDto loadCash(Integer id, DepositUserRequest depositUserRequest) {
        Optional<User> userOptional = userRepository.findUserById(id);
        userOptional.ifPresent(user -> {
            user.setCash(depositUserRequest.getCash());
            userRepository.save(user);
        });
        return userOptional.map(userDtoConverter::converter).orElse(new UserDto());
    }
}
