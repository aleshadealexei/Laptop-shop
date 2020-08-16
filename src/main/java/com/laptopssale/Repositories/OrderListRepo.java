package com.laptopssale.Repositories;

import com.laptopssale.Entities.Order;
import com.laptopssale.Entities.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderListRepo extends JpaRepository<OrderList, Long> {
    List<OrderList> findByOrder(Order id);
}
