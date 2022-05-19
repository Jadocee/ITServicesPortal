package com.spacejaam.itservicesportal.model.client;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;

/**
 *
 */
public enum Role {
    USER,
    ITSTAFF,
    DEV;

    public ArrayList<SimpleGrantedAuthority> getGrantedAuthorities() {
        final ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
