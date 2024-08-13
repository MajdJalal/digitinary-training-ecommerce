package com.digitinary.task3.mapper;

import com.digitinary.task3.dto.UserRequestDto;
import com.digitinary.task3.entity.User;
import com.digitinary.task3.enums.DiscountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();
    }

    @Test
    void testToUser_ShouldMapFieldsCorrectly() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("John Doe");
        userRequestDto.setEmail("john.doe@example.com");
        userRequestDto.setDiscountType(DiscountType.FixedAmountDiscount);

        User user = userMapper.toUser(userRequestDto);

        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals(DiscountType.FixedAmountDiscount, user.getDiscountType());
    }

}