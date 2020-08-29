package com.laptopssale.Controllers;

import com.laptopssale.Entities.Order;
import com.laptopssale.Entities.User;
import com.laptopssale.Repositories.UserRepo;
import com.laptopssale.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private OrderService orderService;
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/{id}")
    public String getProfile(@PathVariable("id") User user, Model model, @AuthenticationPrincipal User user1) {
        if (!user1.getId().equals(user.getId()) || user.equals(null)) {
            return "redirect:/profile/" + user1.getId().toString();
        }
        model.addAttribute("user", user);

        return "profile";
    }
    @GetMapping("/{id}/{mode}")
    public String getProfileMode(@PathVariable("id") User user, @PathVariable String mode, Model model) {
        List<Order> orders = orderService.findAllUserOrders(user);
        model.addAttribute("orders", orders);
        return "profile";
    }
}
