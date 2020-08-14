package com.laptopssale.Repositories;

import com.laptopssale.Entities.Videocard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideocardRepo extends JpaRepository<Videocard,Long> {
    List<Videocard> findAll();

}
