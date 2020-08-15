package com.laptopssale.Repositories;

import com.laptopssale.Entities.Laptop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LaptopRepo extends JpaRepository<Laptop, Long> {
    Optional<Laptop> findById(Long id);
    Laptop findByProductNameAndManufacturerId(String name, Long id);
    List<Laptop> findAllByOrderByIdDesc();
    Page<Laptop> findAll(Pageable pageable);
}
