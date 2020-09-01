package com.laptopssale.Controllers;

import com.laptopssale.Entities.*;
import com.laptopssale.Repositories.*;
import com.laptopssale.Services.EmployeeService;
import com.laptopssale.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
//@PreAuthorize("EMPLOYEE")
@RequestMapping("/employee")
public class EmployeeConroller {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private OrderService orderService;
    @GetMapping
    public String getAddForm() {
        return "addtovartodb";
    }

    @GetMapping("{word}")
    public String getAddAndSearchForm(Model model, @PathVariable(name = "word") String type) {
        employeeService.getAll(model, type);
        return "addtovartodb";
    }




    @PostMapping("/{word}")
    public String addToDb(@PathVariable(name = "word") String type,
                          Manufacturer manufacturer,
                          Videocard videocard,
                          Processor processor,
                          Laptop laptop,
                          @RequestParam(name = "knopka", required = false) String knopka,
                          Model model) {
        employeeService.saveToDatabase(type, manufacturer, videocard, processor, laptop, knopka);
        if (type.equals("laptop1")) {
            type = "laptop";
        }
        return "redirect:/employee/" + type;
    }



    @GetMapping("/order/list")
    public String getOrderList1(Model model) {

        List<Order> orders = orderService.findAllNotCompletedOrders();
        Boolean allOnWarehouse = true;
        for (Order order
                : orders) {
            order.setAllInWarehouse(true);
            for (OrderList orderList:
                    order.getOrderList()) {
                allOnWarehouse = orderList.getLaptop().getCountOnWarehouse() >= orderList.getCount();
                if (!allOnWarehouse) {
                    order.setAllInWarehouse(false);
                    break;
                }
            }
        }
        model.addAttribute("orders", orders);

        return "addtovartodb";
    }

    @GetMapping("/order/list/complete/{id}")
    public String completeOrder(@PathVariable Order id, Model model) {
        employeeService.completeOrder(id);
        return "redirect:/employee";
    }


}
