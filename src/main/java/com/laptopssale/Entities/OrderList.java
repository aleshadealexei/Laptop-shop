package com.laptopssale.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_list")
public class OrderList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numRow;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_number")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "laptop_id")
    private Laptop laptop;



    private Integer count;

    public OrderList() {
    }

    public OrderList(Order order, Laptop laptop, Integer count) {
        //this.isOnWarehouse = count == laptop.getCountOnWarehouse();
        this.order = order;
        this.laptop = laptop;
        this.count = count;
    }



    private Boolean isOnWarehouse;

    public Long getNumRow() {
        return numRow;
    }

    public void setNumRow(Long numRow) {
        this.numRow = numRow;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getOnWarehouse() {
        return isOnWarehouse;
    }

    public void setOnWarehouse(Boolean onWarehouse) {
        isOnWarehouse = onWarehouse;
    }
}
