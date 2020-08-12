package com.laptopssale.SessionAttributes;

import com.laptopssale.Entities.User;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class Cart {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long number;

    public List<Tovar> getTovarList() {
        return tovarList;
    }

    public void setTovarList(List<Tovar> tovarList) {
        this.tovarList = tovarList;
    }

    public Cart(Long number, List<Tovar> tovarList) {
        this.number = number;
        this.tovarList = tovarList;
    }

    private List<Tovar> tovarList;

    public Cart() {
        tovarList = new ArrayList<>();
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
