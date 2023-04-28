package com.kou.bitirme.service;

import com.kou.bitirme.data.entity.User;
import com.kou.bitirme.data.repository.UserRepository;
import com.kou.bitirme.dto.mapper.UserMapper;
import com.kou.bitirme.dto.request.CreateUserRequest;
import com.kou.bitirme.dto.response.UserDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto getUser(String email, String password) {
        User user = userRepository.getUser(email, password);
        return userMapper.toUserDto(user);
    }

    public UserDto createUser(CreateUserRequest request) {
        User result = userRepository.save(userMapper.toUser(request));
        return userMapper.toUserDto(result);
    }

}
