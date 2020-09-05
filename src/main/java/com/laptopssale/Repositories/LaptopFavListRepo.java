package com.laptopssale.Repositories;

import com.laptopssale.Entities.Laptop;
import com.laptopssale.Entities.LaptopsFavList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaptopFavListRepo extends JpaRepository<LaptopsFavList, Long> {
    LaptopsFavList findAllByUserIdAndLaptopId(Long uId, Long lId);
    List<LaptopsFavList> findAllByUserId(Long id);
}