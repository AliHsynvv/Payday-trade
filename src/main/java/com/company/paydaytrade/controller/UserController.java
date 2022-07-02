package com.company.paydaytrade.controller;

import com.company.paydaytrade.dto.CreateUserRequest;
import com.company.paydaytrade.dto.DepositUserRequest;
import com.company.paydaytrade.dto.ResponseDto;
import com.company.paydaytrade.dto.UpdateUserRequest;
import com.company.paydaytrade.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paydaytrade/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ResponseDto> getAllUser() {
        return ResponseEntity.ok(ResponseDto.of(userService.getAllUser()));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(ResponseDto.of(userService.createUser(createUserRequest),
                "User succesfully created"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateUser(@PathVariable Integer id,
                                                  @RequestBody UpdateUserRequest updateUserRequest) {
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

    @PutMapping("/deposit/{id}")
    public ResponseEntity<ResponseDto> loadCash(@PathVariable Integer id,
                                                @RequestBody DepositUserRequest depositUserRequest) {
        return ResponseEntity.ok(ResponseDto.of(userService.loadCash(id, depositUserRequest)));
    }

}
