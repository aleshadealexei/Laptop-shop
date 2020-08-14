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
}
