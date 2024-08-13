package com.digitinary.task3.service.impl;


import com.digitinary.task3.dto.UserRequestDto;
import com.digitinary.task3.entity.User;
import com.digitinary.task3.mapper.UserMapper;
import com.digitinary.task3.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public void createUser(UserRequestDto userRequestDto) {
        User user = userMapper.toUser(userRequestDto);
        userRepository.save(user);
    }
}
