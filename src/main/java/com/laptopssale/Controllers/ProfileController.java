package com.laptopssale.Controllers;

import com.laptopssale.Entities.Order;
import com.laptopssale.Entities.User;
import com.laptopssale.Repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private OrderRepo orderRepo;
    @GetMapping("/{id}")
    public String getProfile(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        return "profile";
    }
    @GetMapping("/{id}/{mode}")
    public String getProfileMode(@PathVariable("id") User user, @PathVariable String mode, Model model) {
        List<Order> orders = orderRepo.findAllByUserId(user.getId());
        model.addAttribute("orders", orders);
        return "profile";
    }
}
