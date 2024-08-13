package com.digitinary.task3.mapper;

import com.digitinary.task3.dto.UserRequestDto;
import com.digitinary.task3.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper

{
    public User toUser(UserRequestDto userRequestDto) {
        return User.builder().
                name(userRequestDto.getName()).
                email(userRequestDto.getEmail()).
                discountType(userRequestDto.getDiscountType()).
                build();
    }
}
