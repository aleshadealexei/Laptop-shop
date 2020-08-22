package com.laptopssale.Repositories;

import com.laptopssale.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findAll();
    List<Order> findAllByUserId(Long id);
    List<Order> findAllByIsCompleted(boolean bool);
}
