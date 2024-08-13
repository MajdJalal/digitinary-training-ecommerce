package com.digitinary.task3.controller;


import com.digitinary.task3.dto.OrderRequestDto;
import com.digitinary.task3.dto.ResponseDto;
import com.digitinary.task3.dto.UserRequestDto;
import com.digitinary.task3.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/ecommerce/users/v1")
public class UserController {

    private final UserService userService;


    /**
     * @param userRequestDto
     * @return ResponseEntity<ResponseDto>
     * calls the createUser method in the userService
     * @author Majd Alkhawaja
     */
    @PostMapping("")
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.builder()
                .statusCode("CREATED")
                .statusMsg("created")
                .build());
    }
}
