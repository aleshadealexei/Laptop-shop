package com.laptopssale.Repositories;

import com.laptopssale.Entities.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepo extends JpaRepository<Manufacturer, Long> {
}
