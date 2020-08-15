package com.laptopssale.Controllers;

import com.laptopssale.Entities.*;
import com.laptopssale.Repositories.LaptopRepo;
import com.laptopssale.Repositories.ManufacturerRepo;
import com.laptopssale.Repositories.ProcessorRepo;
import com.laptopssale.Repositories.VideocardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                          Laptop laptop1,
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
                System.out.println(laptop.getProductName());
                laptop = laptopRepo.findById(laptop.getId()).get();
                System.out.println(laptop.getProductName());
                if (knopka.equals("+1") && laptop.getCountOnWarehouse() > 0) {
                    laptop.setCountOnWarehouse(laptop.getCountOnWarehouse() + 1);
                } else if (laptop.getCountOnWarehouse() > 0)
                {
                    laptop.setCountOnWarehouse(laptop.getCountOnWarehouse() - 1);
                } else if (laptop.getCountOnWarehouse() == 0){
                    laptopRepo.delete(laptop);
                }
                laptopRepo.save(laptop);
                type = "laptop";
                break;
        }
        return "redirect:/employee/" + type;
    }
}
