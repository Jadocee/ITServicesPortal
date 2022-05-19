package com.spacejaam.itservicesportal.model.client;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 *
 */
public class ClientPrinciple implements UserDetails {
    private final Client client;

    public ClientPrinciple(Client client) {
        this.client = client;
    }

    public boolean hasRole(Role role) {
        return role.name().equals(this.client.getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Role.valueOf(this.client.getRole()).getGrantedAuthorities();
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
        return Boolean.parseBoolean(this.client.getIsAccountNonExpired());
    }

    @Override
    public boolean isAccountNonLocked() {
        return Boolean.parseBoolean(this.client.getIsAccountNonLocked());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return Boolean.parseBoolean(this.client.getIsCredentialsNonExpired());
    }

    @Override
    public boolean isEnabled() {
        return Boolean.parseBoolean(this.client.getIsEnabled());
    }
}
