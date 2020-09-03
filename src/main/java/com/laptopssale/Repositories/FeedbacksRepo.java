package com.laptopssale.Repositories;

import com.laptopssale.Entities.Feedback;
import com.laptopssale.Entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface FeedbacksRepo extends JpaRepository<Feedback, Long> {
    List<Feedback> findByLaptopId(Long id);
    Feedback findByUserIdAndLaptopId(Long userid, Long laptopid);
}
