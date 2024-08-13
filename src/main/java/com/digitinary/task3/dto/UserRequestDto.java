package com.digitinary.task3.dto;

import com.digitinary.task3.enums.DiscountType;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {

    private String name;
    private String email;
    private DiscountType discountType;
}
