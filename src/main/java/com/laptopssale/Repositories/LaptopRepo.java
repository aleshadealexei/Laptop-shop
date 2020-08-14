package com.laptopssale.Repositories;

import com.laptopssale.Entities.Laptop;
import com.laptopssale.Entities.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LaptopRepo extends JpaRepository<Laptop, Long> {
    Optional<Laptop> findById(Long id);
    Laptop findByProductNameAndManufacturerId(String name, Long id);
}
