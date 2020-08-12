package com.laptopssale.Entities;

import com.laptopssale.SessionAttributes.Cart;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long orderNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean isCompleted;

    public Order() {

    }

    public Order(Long orderNumber, User user, Boolean isCompleted) {
        this.orderNumber = orderNumber;
        this.user = user;
        this.isCompleted = isCompleted;
    }
}
