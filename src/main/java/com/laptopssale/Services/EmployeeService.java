package com.laptopssale.Services;

import com.laptopssale.Entities.*;
import com.laptopssale.Repositories.*;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class EmployeeService {
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
    public void getAll(Model model, String type) {
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
    }

    public void saveToDatabase(@PathVariable(name = "word") String type,
                               Manufacturer manufacturer,
                               Videocard videocard,
                               Processor processor,
                               Laptop laptop,
                               @RequestParam(name = "knopka", required = false) String knopka) {
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
                if (laptopRepo.findByProductNameAndManufacturerId(laptop.getProductName(),
                        laptop.getManufacturer().getId()) != null) {
                    laptop = laptopRepo.findByProductNameAndManufacturerId(laptop.getProductName(),
                            laptop.getManufacturer().getId());
                    laptop.setCountOnWarehouse(laptop.getCountOnWarehouse() + 1);
                } else {
                    laptop.setCountOnWarehouse(1);
                }
                laptopRepo.save(laptop);
                break;
            case "laptop1":

                laptop = laptopRepo.findById(laptop.getId()).get();
                if (knopka.equals("+1") && laptop.getCountOnWarehouse() >= 0) {
                        laptop
                                .setCountOnWarehouse(laptop.getCountOnWarehouse() + 1);
                    }
                else
                    if (laptop.getCountOnWarehouse() > 0) {
                        laptop
                                .setCountOnWarehouse(laptop.getCountOnWarehouse() - 1);
                    }

                laptopRepo.save(laptop);

                break;
        }
    }

    public void completeOrder(@PathVariable Order id) {
        for(OrderList orderList : id.getOrderList()) {
            orderList.getLaptop().setCountOnWarehouse(
                    orderList.getLaptop().getCountOnWarehouse() - orderList.getCount());
            laptopRepo.save(orderList.getLaptop());
        }
        id.setCompleted(true);
        orderRepo.save(id);
    }

}
