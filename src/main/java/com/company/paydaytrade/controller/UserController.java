package com.company.paydaytrade.controller;

import com.company.paydaytrade.dto.request.CreateUserRequest;
import com.company.paydaytrade.dto.request.DepositUserRequest;
import com.company.paydaytrade.dto.response.ResponseDto;
import com.company.paydaytrade.dto.request.UpdateUserRequest;
import com.company.paydaytrade.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paydaytrade")
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResponseDto> getAllUser() {
        return ResponseEntity.ok(ResponseDto.of(userService.getAllUser()));
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(ResponseDto.of(userService.createUser(createUserRequest),
                "User succesfully created"));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<ResponseDto> updateUser(@PathVariable Integer id,
                                                  @RequestBody @Valid UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(ResponseDto.of(userService.updateUser(id, updateUserRequest)));
    }

    @GetMapping("/admin/email")
    public ResponseEntity<ResponseDto> findUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(ResponseDto.of(userService.findUserByEmail(email)));
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<ResponseDto> findUserByID(@PathVariable Integer id) {
        return ResponseEntity.ok(ResponseDto.of(userService.findUserById(id)));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/deposit/{id}")
    public ResponseEntity<ResponseDto> loadCash(@PathVariable Integer id,
                                                @RequestBody DepositUserRequest depositUserRequest) {
        return ResponseEntity.ok(ResponseDto.of(userService.loadCash(id, depositUserRequest)
                ,"your money has been successfully loaded"));
    }

}
