package com.spacejaam.itservicesportal.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

public enum Role {
    USER(new SimpleGrantedAuthority("ROLE_USER")),
    ITSTAFF(new SimpleGrantedAuthority("ROLE_ITSTAFF")),
    DEV(new SimpleGrantedAuthority("ROLE_ITSTAFF"), new SimpleGrantedAuthority("ROLE_USER"));

    private final Set<SimpleGrantedAuthority> authorities;

    Role(SimpleGrantedAuthority... authorities) {
        this.authorities = Set.of(authorities);
    }

    public Set<SimpleGrantedAuthority> authorities() {
        return this.authorities;
    }
}
