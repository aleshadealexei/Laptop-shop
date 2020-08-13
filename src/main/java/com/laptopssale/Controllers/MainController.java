package com.laptopssale.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @GetMapping
    public String goMainPage() {
        return "redirect:/main";
    }
    @GetMapping("/main")
    public String getMainPage2(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return "main";
    }

}
