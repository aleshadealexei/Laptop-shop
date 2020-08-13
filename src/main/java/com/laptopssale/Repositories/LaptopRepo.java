package com.laptopssale.Repositories;

import com.laptopssale.Entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepo extends JpaRepository<Laptop, Long> {
}
