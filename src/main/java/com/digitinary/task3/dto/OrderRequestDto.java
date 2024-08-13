package com.digitinary.task3.dto;


import com.digitinary.task3.enums.OrderStatus;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequestDto {

    private String description;
    private OrderStatus orderStatus;

}
