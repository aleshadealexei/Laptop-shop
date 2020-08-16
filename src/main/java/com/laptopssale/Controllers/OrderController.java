package com.laptopssale.Controllers;

import com.laptopssale.Entities.Laptop;
import com.laptopssale.Entities.Order;
import com.laptopssale.Entities.OrderList;
import com.laptopssale.Entities.User;
import com.laptopssale.Repositories.OrderListRepo;
import com.laptopssale.Repositories.OrderRepo;
import com.laptopssale.SessionAttributes.Cart;
import org.apache.catalina.Session;
import org.apache.catalina.connector.Request;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController extends Cart {
    @Autowired
    OrderListRepo orderListRepo;

    @Autowired
    OrderRepo orderRepo;
    @GetMapping("/{number}")
    public String redirectCart(@AuthenticationPrincipal User user) throws NullPointerException {
        System.out.println(user.getUsername());
        return "redirect:/check";
    }
    @PostMapping("/make-order")
    public String addOrder(@ModelAttribute(name = "userCart") Cart cart,
                           @AuthenticationPrincipal User user,
                           WebRequest request,
                           SessionStatus sessionStatus,
                           HttpSession session) {
        Order order = new Order(user);
        orderRepo.save(order);

        Order finalorder = orderRepo.findById(order.getId()).get();
        cart.getTovarList().forEach((k,v) ->
        {
            OrderList orderList = new OrderList();
            orderList.setLaptop(k);
            System.out.println(k.getProductName());
            orderList.setCount(v);
            System.out.println(v);
            orderList.setOnWarehouse(v > 0);
            orderList.setOrder(order);
            System.out.println(order.getId());
            orderListRepo.save(orderList);

        });

        session.removeAttribute("userCart");
        System.out.println(cart.getTovarList().size());
        sessionStatus.setComplete();

        session.removeAttribute("userCart");

        return "redirect:/main";
    }

    @GetMapping("/list")
    public String getOrderList1(Model model) {
        List<Order> orders = orderRepo.findAll();

        model.addAttribute("orders", orders);


        return "orderlist";
    }

}
