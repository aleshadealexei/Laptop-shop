package com.laptopssale.Repositories;

import com.laptopssale.Entities.Manufacturer;
import com.laptopssale.Entities.Videocard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManufacturerRepo extends JpaRepository<Manufacturer, Long> {
    List<Manufacturer> findAll();
    List<Manufacturer> findAllByVideoManufacturer(Boolean bool);
    List<Manufacturer> findAllByProcessorManufacturer(Boolean bool);
    List<Manufacturer> findAllByLaptopManufacturer(Boolean bool);
}
