package com.kou.bitirme.controller;

import com.kou.bitirme.dto.request.CreateUserRequest;
import com.kou.bitirme.dto.response.UserDto;
import com.kou.bitirme.service.UserService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDto> getUser(String email, String password) {
        return ResponseEntity.ok(userService.getUser(email, password));
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody CreateUserRequest user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

}
