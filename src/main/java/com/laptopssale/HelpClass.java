package com.laptopssale.Controllers;

import com.laptopssale.Entities.Laptop;
import com.laptopssale.SessionAttributes.Cart;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SessionAttributes("cartInSession")

public class HelpClass {
    public HelpClass() {

    }

    private Set<Laptop> laptopList = new HashSet<>();
    public void getMainPage(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(
                "cartInSession",
                new Cart());

    }

    public void setSessionUser(HttpServletRequest request, Laptop laptop) {
        Cart cart = (Cart)request.getSession().getAttribute("cartInSession");
        laptopList.add(laptop);
        request.getSession().setAttribute("cartInSession", cart);

    }


    public void getSessionAttributes(Model model, HttpServletRequest request) {


    }
}
