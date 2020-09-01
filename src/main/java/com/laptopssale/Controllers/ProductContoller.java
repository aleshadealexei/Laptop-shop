package com.laptopssale.Controllers;

import com.laptopssale.Entities.Laptop;
import com.laptopssale.SessionAttributes.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/product")

public class ProductContoller {
    @GetMapping("/{id}")
    public String getInfo(HttpSession session, @PathVariable(name = "id") Laptop laptop, Model model) {
        if (session.getAttribute("userCart") == null)
            session.setAttribute("userCart", new Cart(session));
        System.out.println("aa");
        model.addAttribute("laptop", laptop);
        return "productinfo";
    }
}
