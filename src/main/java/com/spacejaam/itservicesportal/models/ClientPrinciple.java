package com.spacejaam.itservicesportal.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class ClientPrinciple implements UserDetails {
    private final Client client;

    public ClientPrinciple(Client client) {
        this.client = client;
    }

    public Long getId() {
        return this.client.getId();
    }

    public String getName() {
        return this.client.getFirstName() + " " + this.client.getLastName();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.client.getRole().authorities();
    }

    @Override
    public String getPassword() {
        return this.client.getPassword();
    }

    @Override
    public String getUsername() {
        return this.client.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.client.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.client.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.client.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.client.isEnabled();
    }
}
