package com.laptopssale.Controllers;

import com.laptopssale.Entities.Laptop;
import com.laptopssale.Repositories.LaptopRepo;
import com.laptopssale.Repositories.ProcessorRepo;
import com.laptopssale.SessionAttributes.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController extends Cart {
    @Autowired
    private ProcessorRepo processorRepo;


    @Autowired
    private LaptopRepo laptopRepo;

    @GetMapping
    public String redirectCatalog() {
        return "redirect:/main";


    }

    @GetMapping("/main")
    public String getMainPage(HttpSession session,

                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 2) Pageable pageable,
                              Model model) {

        if (session.getAttribute("userCart") == null)
            session.setAttribute("userCart", new Cart(session));

        session.setAttribute("whereRedirect", "main");
        Page<Laptop> laptopPage = laptopRepo.findAll(pageable);
        model.addAttribute("page", laptopPage);
        model.addAttribute("laptops", laptopRepo.findAll());
        return "main";
    }

    @GetMapping("/laptop/add-to-cart/{id}")
    public String addLaptopToCart(HttpServletRequest httpRequest,
                                  HttpSession session,
                                  @PathVariable Laptop id,
                                  Model model) {

        Cart cart;
        if (session.getAttribute("userCart") == null)
            session.setAttribute("userCart", new Cart(session));
        cart = (Cart) session.getAttribute("userCart");
        if (!cart.hasOnCart(id)) {
            cart.getTovarList().put(id, 1);
        }
        cart.setSum(cart.getSum() + id.getPriceToSale());
        model.addAttribute("laptops", laptopRepo.findAll());
        session.setAttribute("userCart", cart);
        if (session.getAttribute("whereRedirect").equals("check")) {
            return "redirect:/check";
        }
        return "redirect:/main";
    }

    @GetMapping("/laptop/del-from-cart/{id}")
    public String delLaptopFromCart(HttpSession session, @PathVariable Laptop id, Model model) {
        Cart cart;
        if (session.getAttribute("userCart") == null) {
            session.setAttribute("userCart", new Cart());
            cart = new Cart();
        } else {
            cart = (Cart) session.getAttribute("userCart");
        }
        if (cart.hasOnCart(id, 1)) {
            System.out.println("Содержит");
            cart.setSum(cart.getSum() - id.getPriceToSale());
            session.setAttribute("userCart", cart);

        }

        model.addAttribute("laptops", laptopRepo.findAll());
        session.setAttribute("userCart", cart);

        return "redirect:/check";
    }

    @GetMapping("/check")
    public String checkCart(Model model, HttpSession session) {
        session.setAttribute("whereRedirect", "check");
        if (session.getAttribute("userCart") == null)
            session.setAttribute("userCart", new Cart(session));
        Cart cart = (Cart) session.getAttribute("userCart");
        model.addAttribute("cart", cart);
        return "second";
    }

}