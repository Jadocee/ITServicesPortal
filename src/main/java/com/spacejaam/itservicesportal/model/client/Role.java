package com.spacejaam.itservicesportal.model.client;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
public enum Role {
    USER(new SimpleGrantedAuthority("ROLE_USER")),
    ITSTAFF(new SimpleGrantedAuthority("ROLE_ITSTAFF")),
    DEV(new SimpleGrantedAuthority("ROLE_ITSTAFF"), new SimpleGrantedAuthority("ROLE_USER"));

    private final ArrayList<SimpleGrantedAuthority> authorities;

    Role(SimpleGrantedAuthority... authorities) {
        this.authorities = new ArrayList<>();
        this.authorities.addAll(Arrays.asList(authorities));
    }

    public ArrayList<SimpleGrantedAuthority> authorities() {
        return this.authorities;
    }
}
