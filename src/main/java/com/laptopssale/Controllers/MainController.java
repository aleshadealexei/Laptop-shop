package com.laptopssale.Controllers;

import com.laptopssale.Entities.Processor;
import com.laptopssale.Repositories.ProcessorRepo;
import com.laptopssale.SessionAttributes.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("userCart" )
public class MainController  {
    @Autowired
    private ProcessorRepo processorRepo;

    @GetMapping
    public String redirectCatalog() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String getMainPage(HttpSession session, Model model) {
        session.setAttribute(
                "userCart",
                new Cart());
        model.addAttribute("processors", processorRepo.findAll());
        return "main";
    }

    @PostMapping("/main")
    public String addLaptopToCart(HttpSession session, @RequestParam Processor id, Model model) {
        Cart cart = (Cart)session.getAttribute("userCart");
        cart.getTovarList().add(id);
        System.out.println("Отправлен пост");
        model.addAttribute("processors", processorRepo.findAll());
        session.setAttribute("userCart", cart);
        return "main";
    }

    @GetMapping("/check")
    public String checkCart(Model model, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("userCart");
        model.addAttribute("tovari", cart.getTovarList());
        return "second";
    }
}