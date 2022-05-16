package com.spacejaam.itservicesportal.bean.client;

import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 */
public interface ClientDetails extends UserDetails {
    void setId(Long id);

    void setUsername(String email);

    void setPassword(String password);

    void setFirstName(String firstName);

    void setLastName(String lastName);

    void setRole(String role);

    void setIssAccountNonExpired(String issAccountNonExpired);

    void setIsAccountNonLocked(String isAccountNonLocked);

    void setIsEnabled(String isEnabled);

    void setIsCredentialsNonExpired(String isCredentialsNonExpired);
}
