package com.digitinary.task3.entity;

import com.digitinary.task3.enums.OrderStatus;
import com.digitinary.task3.service.observer.OrderPublisher;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "orders")
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private  Long id;
    private String dscription;
    private User user;
    private OrderStatus orderStatus;

    //ManyToOne Relationship
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }



    @Column(name = "order_description")
    public String getDscription() {
        return dscription;
    }

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
