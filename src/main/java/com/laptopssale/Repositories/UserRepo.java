package com.laptopssale.Repositories;

import com.laptopssale.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByActivationCode(String activeCode);

}
