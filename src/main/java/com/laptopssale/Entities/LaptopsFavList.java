package com.laptopssale.Entities;

import javax.persistence.*;


@Entity
@Table(name = "laptopsFavList")
public class LaptopsFavList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "laptop_id")
    private Laptop laptop;

    public LaptopsFavList() {
    }

    public LaptopsFavList(User user, Laptop laptop) {
        this.user = user;
        this.laptop = laptop;
    }

    public LaptopsFavList(Long id, User user, Laptop laptop) {
        this.id = id;
        this.user = user;
        this.laptop = laptop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }
}
