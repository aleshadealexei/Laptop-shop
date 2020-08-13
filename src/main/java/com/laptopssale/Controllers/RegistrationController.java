package com.laptopssale.Controllers;

import com.laptopssale.Entities.User;
import com.laptopssale.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String getRegPage() {
        return "registration";
    }

    @PostMapping
    public String addNewUser(User user) {
        userService.addUser(user);
        return "redirect:/main";
    }
}
