package com.laptopssale.SessionAttributes;

import com.laptopssale.Entities.Laptop;
import com.laptopssale.Entities.Processor;
import com.laptopssale.Entities.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.servlet.http.HttpSession;
import java.util.*;

public class Cart {
    private Map<Laptop, Integer> tovarList;
    private Double sum;


    public Map<Laptop, Integer> getTovarList() {
        return tovarList;
    }

    public void setTovarList(Map<Laptop, Integer> tovarList) {
        this.tovarList = tovarList;
    }


    public Cart() {

        tovarList = new HashMap<>();
        this.sum = (double) 0;
    }

    public Cart(HttpSession session) {
        session.setAttribute("userCart", this);
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
            if (key.getId() == laptop.getId()) {
                tovarList.replace(key, tovarList.get(key) + 1);
                sum += laptop.getPriceToSale();
                return true;
            }

        }
        return false;
    }

    public boolean hasOnCart(Laptop laptop, int i) {

        for (Laptop key: tovarList.keySet()) {
            if (key.getId() == laptop.getId()) {
                tovarList.replace(key, tovarList.get(key) - 1);
                if (tovarList.get(key) == 0) {
                    tovarList.remove(key);
                }
                return true;
            }

        }
        return false;
    }

    public int getCount() {
        int count = 0;
        for (Map.Entry<Laptop, Integer> entry : tovarList.entrySet()) {
            count += entry.getValue();
        }
        return count;
    }
}
