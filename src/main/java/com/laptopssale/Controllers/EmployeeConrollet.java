package com.laptopssale.Controllers;

import com.laptopssale.Entities.*;
import com.laptopssale.Repositories.LaptopRepo;
import com.laptopssale.Repositories.ManufacturerRepo;
import com.laptopssale.Repositories.ProcessorRepo;
import com.laptopssale.Repositories.VideocardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
                model.addAttribute("laptops", laptopRepo.findAll());
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
        }
        return "redirect:/employee/" + type;
    }
}
