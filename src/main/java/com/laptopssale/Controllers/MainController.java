package com.laptopssale.Controllers;

import com.laptopssale.Entities.Laptop;
import com.laptopssale.Entities.Order;
import com.laptopssale.Entities.Processor;
import com.laptopssale.Entities.User;
import com.laptopssale.Repositories.LaptopRepo;
import com.laptopssale.Repositories.ProcessorRepo;
import com.laptopssale.SessionAttributes.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                              //@ModelAttribute(name = "userCart") Cart cart,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 2) Pageable pageable,
                              Model model) {

            session.setAttribute("userCart", new Cart());

        Page<Laptop> laptopPage = laptopRepo.findAll(pageable);
        model.addAttribute("page", laptopPage);
        model.addAttribute("laptops", laptopRepo.findAll());
        return "main";
    }

    @GetMapping("/laptop/add-to-cart/{id}")
    public String addLaptopToCart(HttpSession session, @PathVariable Laptop id, Model model) {
        Cart cart =(Cart) session.getAttribute("userCart");
        if (cart.hasOnCart(id)) {
        } else {
            System.out.println("Не содержит");
            cart.getTovarList().put(id, 1);
        }
        cart.setSum(cart.getSum() + id.getPriceToSale());
        //System.out.println("Отправлен пост");
        model.addAttribute("laptops", laptopRepo.findAll());
        session.setAttribute("userCart", cart);
        return "redirect:/main";
    }

    @GetMapping("/check")
    public String checkCart(Model model, HttpSession session, @ModelAttribute("userCart") Cart cart) {
        if (cart == null) {
            cart = new Cart();
        }
        model.addAttribute("summa", cart.getSum());
        model.addAttribute("tovari", cart.getTovarList());
        return "second";
    }
}