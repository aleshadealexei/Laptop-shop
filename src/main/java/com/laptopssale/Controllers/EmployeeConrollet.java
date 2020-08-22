package com.laptopssale.Controllers;

import com.laptopssale.Entities.*;
import com.laptopssale.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
//@PreAuthorize("EMPLOYEE")
@RequestMapping("/employee")
public class EmployeeConrollet {
    @Autowired
    ManufacturerRepo manufacturerRepo;
    @Autowired
    VideocardRepo videocardRepo;
    @Autowired
    ProcessorRepo processorRepo;
    @Autowired
    LaptopRepo laptopRepo;
    @Autowired
    OrderRepo orderRepo;
    @GetMapping
    public String getAddForm() {
        return "addtovartodb";
    }

    @GetMapping("{word}")
    public String getAddAndSearchForm(Model model, @PathVariable(name = "word") String type) {
        switch (type) {
            case "manufacturer":
                model.addAttribute("manufacturers", manufacturerRepo.findAll());
                break;
            case "videocard":
                model.addAttribute("videocards", videocardRepo.findAll());
                model.addAttribute("manufacturers", manufacturerRepo.findAllByVideoManufacturer(true));
                model.addAttribute("types", VideoMemory.values());
                break;
            case "processor":
                model.addAttribute("processors",processorRepo.findAll());
                model.addAttribute("manufacturers", manufacturerRepo.findAllByProcessorManufacturer(true));
                break;
            case "laptop":
                model.addAttribute("manufacturers", manufacturerRepo.findAllByLaptopManufacturer(true));
                model.addAttribute("videocardssa", videocardRepo.findAll());
                model.addAttribute("processors", processorRepo.findAll());
                model.addAttribute("laptops", laptopRepo.findAllByOrderByIdDesc());
                break;
        }
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

        switch (type) {
            case "manufacturer":
                manufacturerRepo.save(manufacturer);
                break;
            case "videocard":
                videocardRepo.save(videocard);
                break;
            case "processor":
                processorRepo.save(processor);
                break;
            case "laptop":
                if (laptopRepo.findByProductNameAndManufacturerId(laptop.getProductName(), laptop.getManufacturer().getId()) != null) {
                    laptop = laptopRepo.findByProductNameAndManufacturerId(laptop.getProductName(), laptop.getManufacturer().getId());
                    laptop.setCountOnWarehouse(laptop.getCountOnWarehouse() + 1);
                } else {
                    laptop.setCountOnWarehouse(1);
                }
                laptopRepo.save(laptop);
                break;
            case "laptop1":
                laptop = laptopRepo.findById(laptop.getId()).get();
                if (knopka.equals("+1") && laptop.getCountOnWarehouse() >= 0) {
                    laptop.setCountOnWarehouse(laptop.getCountOnWarehouse() + 1);
                } else if (laptop.getCountOnWarehouse() > 0)
                {
                    laptop.setCountOnWarehouse(laptop.getCountOnWarehouse() - 1);
                }
                laptopRepo.save(laptop);
                type = "laptop";
                break;
        }
        return "redirect:/employee/" + type;
    }

    @GetMapping("/order/list")
    public String getOrderList1(Model model) {
        List<Order> orders = orderRepo.findAllByIsCompleted(false);
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
        List<Laptop> laptopList;
        for(OrderList orderList : id.getOrderList()) {
            orderList.getLaptop().setCountOnWarehouse(
                    orderList.getLaptop().getCountOnWarehouse() - orderList.getCount());
            laptopRepo.save(orderList.getLaptop());
        }
        id.setCompleted(true);
        orderRepo.save(id);
        return "redirect:/employee";
    }
}
