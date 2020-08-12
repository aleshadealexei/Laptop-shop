package com.laptopssale.SessionAttributes;

import com.laptopssale.Entities.User;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Component
public class Cart {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long number;

    private User user;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
