package com.laptopssale.Controllers;

import com.laptopssale.Entities.User;
import com.laptopssale.SessionAttributes.Cart;
import com.laptopssale.SessionAttributes.Tovar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("cartInSession")

public class MainController {
    @GetMapping("/main")
    public String getMainPage(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(
                "cartInSession",
                new Cart());
        return "main";
    }

    @PostMapping("/main")
    public String setSessionUser(HttpServletRequest request, Tovar tovar) {
        Cart cart = (Cart)request.getSession().getAttribute("cartInSession");
        cart.getTovarList().add(tovar);
        request.getSession().setAttribute("cartInSession", cart);
        return "main";
    }

    @GetMapping("/check")
    public String getSessionAttributes(Model model, HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cartInSession");
        model.addAttribute("tovari", cart.getTovarList());
        return "second";
    }
}
