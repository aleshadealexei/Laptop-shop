package com.laptopssale.SessionAttributes;

import com.laptopssale.Entities.Processor;
import com.laptopssale.Entities.User;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class Cart {

    public List<Processor> getTovarList() {
        return tovarList;
    }

    public void setTovarList(List<Processor> tovarList) {
        this.tovarList = tovarList;
    }

    public Cart(Long number, List<Processor> tovarList) {
        this.tovarList = tovarList;
    }

    private List<Processor> tovarList;

    public Cart() {
        tovarList = new ArrayList<>();
    }


}
