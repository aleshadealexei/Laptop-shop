package com.laptopssale.Controllers;

import com.laptopssale.Entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @GetMapping("/{id}")
    public String getProfile(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        return "profile";
    }
    @GetMapping("/{id}/{mode}")
    public String getProfileMode(@PathVariable("id") User user, @PathVariable String mode, Model model) {
        model.addAttribute("user", user);
        return "profile";
    }
}
