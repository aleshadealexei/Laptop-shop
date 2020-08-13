package com.laptopssale.SessionAttributes;

import com.laptopssale.Entities.Laptop;

import java.util.HashSet;
import java.util.List;

public class Cart {
    private List<Laptop> cartList;

    public List<Laptop> getCartList() {
        return cartList;
    }

    public void setCartList(List<Laptop> cartList) {
        this.cartList = cartList;
    }

    public Cart() {
    }

    public Cart(List<Laptop> cartList) {
        this.cartList = cartList;
    }
}
