package com.laptopssale.Entities;


public class User {

    private Long id;

    private String username;

    private String password;

    public User() {
    }

    public User(String username, String passwrod) {
        this.username = username;
        this.password = passwrod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
