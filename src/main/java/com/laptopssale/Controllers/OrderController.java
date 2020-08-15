package com.laptopssale.Controllers;

import com.laptopssale.SessionAttributes.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController extends Cart {
    @GetMapping
    public String redirectCart() {
        return "redirect:/check";
    }

}
