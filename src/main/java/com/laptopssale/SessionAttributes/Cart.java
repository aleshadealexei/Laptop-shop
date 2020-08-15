package com.laptopssale.SessionAttributes;

import com.laptopssale.Entities.Laptop;
import com.laptopssale.Entities.Processor;
import com.laptopssale.Entities.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.*;

@SessionAttributes("userCart")
public class Cart {

    protected Map<Laptop, Integer> getTovarList() {
        return tovarList;
    }

    public void setTovarList(Map<Laptop, Integer> tovarList) {
        this.tovarList = tovarList;
    }

    private Map<Laptop, Integer> tovarList;
    private Double sum;

    public Cart() {
        tovarList = new HashMap<>();
        this.sum = (double) 0;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public boolean hasOnCart(Laptop laptop) {

        for (Laptop key: tovarList.keySet()) {
            if (key.getId().equals(laptop.getId())) {
                tovarList.replace(key, tovarList.get(key) + 1);
                return true;
            }

        }
        return false;
    }
}
