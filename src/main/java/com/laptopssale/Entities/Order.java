package com.laptopssale.Entities;

import org.hibernate.tuple.GeneratedValueGeneration;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderList> orderList;

    private Boolean isCompleted;

    private Double sum;

    @Transient
    private Boolean allInWarehouse;


    public Order() {

    }

    public Order(User user) {

        this.user = user;
        this.isCompleted = false;
    }

    public Double getSum() {
        return sum;
    }

    public Boolean getAllInWarehouse() {
        return allInWarehouse;
    }

    public void setAllInWarehouse(Boolean allInWarehouse) {
        this.allInWarehouse = allInWarehouse;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderList> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderList>  orderList) {
        this.orderList = orderList;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
