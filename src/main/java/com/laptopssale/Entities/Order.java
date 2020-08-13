package com.laptopssale.Entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Set<Laptop> laptopList;

    private Boolean isCompleted;

    public Order() {

    }

    public Order(Long orderNumber, User user, Boolean isCompleted) {
        this.orderNumber = orderNumber;
        this.user = user;
        this.isCompleted = isCompleted;
    }
}
