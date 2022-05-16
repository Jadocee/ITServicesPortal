package com.spacejaam.itservicesportal.bean.client;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

//@Component("clientBean")
//@Scope(value = "session")
@Table(name = "clients")
public class Client implements UserDetails {


    @Column("firstName")
    private final String firstName;
    @Column("lastName")
    private final String lastName;
    @Column("email")
    private final String email;
    @Column("password")
    private final String password;
    @Column("contactNum")
    private final String contactNum;
    @Column("role")
    private final String role;
    @Id
    @Column("ID")
    private Long id;

    public Client(Long id, String firstName, String lastName, String email, String password, String contactNum, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contactNum = contactNum;
        this.role = role;
    }


    public String getRoleLabel() {
        return this.role;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }


    public String getContactNum() {
        return contactNum;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getRole() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}
