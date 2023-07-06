package com.kou.bitirme.service;

import com.kou.bitirme.data.entity.User;
import com.kou.bitirme.data.repository.UserRepository;
import com.kou.bitirme.dto.mapper.UserMapper;
import com.kou.bitirme.dto.request.CreateUserRequest;
import com.kou.bitirme.dto.response.UserDto;
import com.kou.bitirme.exception.UserNotFoundException;
import com.kou.bitirme.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto login(String email, String password) {
        User user = userRepository.getUser(email, password).orElseThrow(() -> new UserNotFoundException("User Not Found!"));

        final String token = jwtUtil.generateToken(user.getId().toString());

        UserDto result = userMapper.toUserDto(user);
        result.setToken(token);

        return result;
    }

    public UserDto register(CreateUserRequest request) {
        User result = userRepository.save(userMapper.toUser(request));
        return userMapper.toUserDto(result);
    }

    public User getUserById(String id) {
        return userRepository.findById(UUID.fromString(id)).orElseThrow(() -> new UserNotFoundException("User Not Found!"));
    }

}
