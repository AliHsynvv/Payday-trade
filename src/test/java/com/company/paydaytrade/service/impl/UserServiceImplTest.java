package com.company.paydaytrade.service.impl;

import com.company.paydaytrade.dto.request.CreateUserRequest;
import com.company.paydaytrade.entity.User;
import com.company.paydaytrade.repository.UserRepository;
import com.company.paydaytrade.util.UserDtoConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    private UserServiceImpl userService;
    private final UserDtoConverter userDtoConverter = new UserDtoConverter();
    private  PasswordEncoder passwordEncoder=new BCryptPasswordEncoder() ;


    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, userDtoConverter, passwordEncoder);
    }


    @Test
    void canGetAllUser() {
        //when
        userService.getAllUser();
        //then
        verify(userRepository).findAll();

    }

    @Test
    void canCreateUser() {
        //given
        User u = new User();
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setName("Ali");
        createUserRequest.setPassword(passwordEncoder.encode("0"));
        createUserRequest.setEmail("g");
        createUserRequest.setCash(12D);
        //when
        userService.createUser(createUserRequest);
        //then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();
        assertThat(userDtoConverter.converter(capturedUser)).isEqualTo(createUserRequest);
    }

    @Test
    void updateUser() {
    }

    @Test
    void findUserByEmail() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void loadCash() {
    }
}