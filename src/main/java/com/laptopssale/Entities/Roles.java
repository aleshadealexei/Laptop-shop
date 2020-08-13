package com.laptopssale.Entities;


import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    ADMIN, EMPLOYEE, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
