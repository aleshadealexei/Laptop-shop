package com.laptopssale.Services;

import com.laptopssale.Entities.Order;
import com.laptopssale.Entities.OrderList;
import com.laptopssale.Entities.User;
import com.laptopssale.Repositories.OrderListRepo;
import com.laptopssale.Repositories.OrderRepo;
import com.laptopssale.SessionAttributes.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderListRepo orderListRepo;

    public void createOrder(Cart cart, User user) {
        Order order = new Order(user);
        order.setSum(cart.getSum());
        orderRepo.save(order);
        System.out.println("Я потерялся :С");
        Order finalorder = orderRepo.findById(order.getId()).get();
        cart.getTovarList().forEach((k, v) ->
        {
            OrderList orderList = new OrderList();
            orderList.setLaptop(k);

            orderList.setCount(v);

            orderList.setOnWarehouse(v > 0);
            orderList.setOrder(order);

            orderListRepo.save(orderList);

        });
    }

    public List<Order> findAllUserOrders(User user) {
        return orderRepo.findAllByUserId(user.getId());
    }

    public  List<Order> findAllNotCompletedOrders() {
        return orderRepo.findAllByIsCompleted(false);
    }


}
