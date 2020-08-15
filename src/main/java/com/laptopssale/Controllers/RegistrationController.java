package com.laptopssale.Controllers;

import com.laptopssale.Entities.User;
import com.laptopssale.Repositories.UserRepo;
import com.laptopssale.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @GetMapping
    public String getRegPage() {
        return "registration";
    }

    @PostMapping
    public String addNewUser(User user) {
        if (userRepo.findByUsername(user.getUsername()) == null) {
            userService.addUser(user);
        }

        return "redirect:/main";
    }
    @GetMapping("/{code}")
    public String getLogin(@PathVariable String code) {
        User user = userRepo.findByActivationCode(code);
        if (user != null) {
            user.setActivationCode(null);
            userRepo.save(user);
        }
        return "redirect:/main";
    }
}
