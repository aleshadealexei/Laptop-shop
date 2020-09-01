package com.laptopssale.Controllers;

import com.laptopssale.Entities.Order;
import com.laptopssale.Entities.User;
import com.laptopssale.Repositories.UserRepo;
import com.laptopssale.Services.OrderService;
import com.laptopssale.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public String getProfile(@PathVariable("id") User user, Model model, @AuthenticationPrincipal User user1) {
        if (!user1.getId().equals(user.getId()) || user.equals(null)) {
            return "redirect:/profile/" + user1.getId().toString();
        }
        model.addAttribute("user", user);

        return "profile";
    }
    @GetMapping("/{id}/{mode}")
    public String getProfileMode(@PathVariable("id") User user,
                                 @PathVariable String mode,
                                 Model model
                                 ) {
        if (mode.equals("settings")) {
            model.addAttribute("user", user);
        }
        if (mode.equals("orders")) {
            List<Order> orders = orderService.findAllUserOrders(user);
            model.addAttribute("orders", orders);
        }
        return "profile";
    }

    @PostMapping("/{id}/settings")
    public String editProfile( User userEdit,
                              @PathVariable(name = "id") User user1,
                              @AuthenticationPrincipal User user,
                              Model model) {
        if (!user1.getId().equals( user.getId())) {
            return "redirect:/profile/" + user.getId().toString() + "/settigns";
        }
        userService.changeProfile(user1, userEdit);
        return "redirect:/profile/" + user.getId().toString();
    }
}
