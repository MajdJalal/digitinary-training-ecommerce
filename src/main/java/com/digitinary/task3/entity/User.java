package com.digitinary.task3.entity;

import com.digitinary.task3.enums.DiscountType;
import com.digitinary.task3.service.observer.UserSubscriber;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private DiscountType discountType;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    public DiscountType getDiscountType() {
        return discountType;
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    @Column(name = "user_name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    @Column(name = "user_email")
    public String getEmail() {
        return email;
    }
}
