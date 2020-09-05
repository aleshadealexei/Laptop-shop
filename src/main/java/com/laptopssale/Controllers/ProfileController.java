package com.laptopssale.Controllers;

import com.laptopssale.Entities.LaptopsFavList;
import com.laptopssale.Entities.Order;
import com.laptopssale.Entities.User;
import com.laptopssale.Repositories.LaptopFavListRepo;
import com.laptopssale.Repositories.UserRepo;
import com.laptopssale.Services.OrderService;
import com.laptopssale.Services.UserService;
import com.laptopssale.SessionAttributes.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private LaptopFavListRepo laptopFavListRepo;
    @Autowired
    private HttpSession session;

    public String redirectToYourProfile(User user, User user1) {
        if (session.getAttribute("userCart") == null)
            session.setAttribute("userCart", new Cart());
        if (!user1.getId().equals(user.getId()) || user.equals(null)) {

        } else
            return null;
        return "redirect:/profile/" + user1.getId().toString();
    }

    @GetMapping("/")
    private String redirectToData(@AuthenticationPrincipal User user) {
        return "redirect:/profile/" + user.getId().toString();
    }

    @GetMapping("/{id}")
    public String getProfile(@PathVariable("id") User user, Model model, @AuthenticationPrincipal User user1) {
        if (redirectToYourProfile(user, user1) != null)  {

        }
            model.addAttribute("user", user);

        return "profile";
    }



    @GetMapping("/{id}/{mode}")
    public String getProfileMode(@PathVariable("id") User user,
                                 @PathVariable String mode,
                                 @AuthenticationPrincipal User user1,
                                 Model model
                                 ) {
        if (redirectToYourProfile(user, user1) != null)  {

        }
        model.addAttribute("user", user);
        if (mode.equals("settings")) {
            model.addAttribute("user", user);
        }
        if (mode.equals("orders")) {
            List<Order> orders = orderService.findAllUserOrders(user);
            model.addAttribute("orders", orders);
        }
        if (mode.equals("favlist")) {
            model.addAttribute("laptops", laptopFavListRepo.findAllByUserId(user.getId()));
        }
        return "profile";
    }

    @PostMapping("/{id}/settings")
    public String editProfile( User userEdit,
                              @PathVariable(name = "id") User user,
                              @AuthenticationPrincipal User user1,
                              Model model) {
        if (redirectToYourProfile(user, user1) != null)  {

        }
        userService.changeProfile(user1, userEdit);
        return "redirect:/profile/" + user.getId().toString();
    }
}
