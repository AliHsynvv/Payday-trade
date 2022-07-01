package com.example.paydaytrade.controller;

import com.example.paydaytrade.dto.CreateUserRequest;
import com.example.paydaytrade.dto.ResponseDto;
import com.example.paydaytrade.dto.UpdateUserRequest;
import com.example.paydaytrade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/paydaytrade/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<ResponseDto> getAllUser() {
        return ResponseEntity.ok(ResponseDto.of(userService.getAllUser()));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(ResponseDto.of(userService.createUser(createUserRequest), "User succesfully created"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateUser(@PathVariable Integer id, @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(ResponseDto.of(userService.updateUser(id, updateUserRequest)));
    }

    @GetMapping("/v1/{email}")
    public ResponseEntity<ResponseDto> findUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(ResponseDto.of(userService.findUserByEmail(email)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> findUserByID(@PathVariable Integer id) {
        return ResponseEntity.ok(ResponseDto.of(userService.findUserById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
