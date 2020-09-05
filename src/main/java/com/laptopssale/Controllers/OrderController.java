package com.laptopssale.Controllers;

import com.laptopssale.Entities.Laptop;
import com.laptopssale.Entities.Order;
import com.laptopssale.Entities.OrderList;
import com.laptopssale.Entities.User;
import com.laptopssale.Repositories.OrderListRepo;
import com.laptopssale.Repositories.OrderRepo;
import com.laptopssale.Services.OrderService;
import com.laptopssale.SessionAttributes.Cart;
import org.apache.catalina.connector.Request;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
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
public class OrderController  {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{number}")
    public String redirectCart(@AuthenticationPrincipal User user,
                               @PathVariable Order number,
                               Model model) throws NullPointerException {

        model.addAttribute("orders", number);
        return "redirect:/check";
    }

    @PostMapping("/make-order")
    public String addOrder(
                           @RequestParam String knopka,
                           @AuthenticationPrincipal User user,
                           SessionStatus sessionStatus,
                           HttpSession session) {
        Cart cart = (Cart) session.getAttribute("userCart");
        orderService.createOrder(cart, user);
        session.removeAttribute("userCart");
        sessionStatus.setComplete();
        return "redirect:/main";
    }




}
