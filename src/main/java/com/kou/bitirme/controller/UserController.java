package com.kou.bitirme.controller;

import com.kou.bitirme.dto.request.CreateUserRequest;
import com.kou.bitirme.dto.response.UserDto;
import com.kou.bitirme.service.UserService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDto> login(@RequestParam String email, @RequestParam String password) {
        return ResponseEntity.ok(userService.login(email, password));
    }

    @PostMapping
    public ResponseEntity<Object> register(@Valid @RequestBody CreateUserRequest user) {
        return ResponseEntity.ok(userService.register(user));
    }

}
