package com.laptopssale.Controllers;

import com.laptopssale.Entities.Feedback;
import com.laptopssale.Entities.Laptop;
import com.laptopssale.Entities.LaptopsFavList;
import com.laptopssale.Entities.User;
import com.laptopssale.Repositories.FeedbacksRepo;
import com.laptopssale.Repositories.LaptopFavListRepo;
import com.laptopssale.Repositories.LaptopRepo;
import com.laptopssale.Repositories.UserRepo;
import com.laptopssale.SessionAttributes.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/product")

public class ProductContoller {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FeedbacksRepo feedbacksRepo;

    @Autowired
    private LaptopFavListRepo laptopFavListRepo;

    @Autowired
    private LaptopRepo laptopRepo;
    @GetMapping("/{id}")
    public String getInfo(HttpSession session,
                          @PathVariable(name = "id") Laptop laptop,
                          Model model) {
        if (session.getAttribute("userCart") == null)
            session.setAttribute("userCart", new Cart(session));
        model.addAttribute("keke", false);
        model.addAttribute("laptop", laptop);
        model.addAttribute("feedbacks", feedbacksRepo.findByLaptopId(laptop.getId()));
        model.addAttribute("feedbacks", feedbacksRepo.findByLaptopId(laptop.getId()));
        return "productinfo";
    }

    @PostMapping("/{id}")
    public String setFeedback(HttpSession session,
                              Feedback feedback,
                              @AuthenticationPrincipal User user,
                              @PathVariable(name = "id") Laptop laptop,
                              Model model) {
        if (feedbacksRepo.findByUserIdAndLaptopId(user.getId(), laptop.getId()) == null) {
            feedback.setUser(user);
            System.out.println(feedback.getUser().getUsername());
            feedbacksRepo.save(feedback);
        }
        return "redirect:/product/" + laptop.getId();
    }

    @GetMapping("/{id}/fav")
    public String addAndRemoveFavList(@PathVariable(name = "id") Laptop laptop,
                                      @AuthenticationPrincipal User user,
                                      Model model) {
        LaptopsFavList laptopsFavList = laptopFavListRepo.findAllByUserIdAndLaptopId(user.getId(), laptop.getId());
        if (laptopsFavList == null) {
            laptopsFavList = new LaptopsFavList(user, laptop) ;
            laptopFavListRepo.save(laptopsFavList);
        } else {
            laptopFavListRepo.delete(laptopsFavList);
        }
        return "redirect:/product/" + laptop.getId();
    }
}
