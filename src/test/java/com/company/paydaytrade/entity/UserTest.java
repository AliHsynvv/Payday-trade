package com.company.paydaytrade.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    User user;

    @BeforeEach
     void setUp() {
        user = new User();
    }

    @Test
    public void getName() {
        String name = "Aliakbar";
        user.setName(name);
        assertEquals(name, user.getName());
    }

    @Test
    public void getEmail() {
        String email = "gsynvali@gmail.com";
        user.setEmail(email);
        assertEquals(email,user.getEmail());
    }
}