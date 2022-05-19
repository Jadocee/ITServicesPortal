package com.spacejaam.itservicesportal.model.client;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

//@Component("clientBean")
//@Scope(value = "session")
@Table(name = "Client")
public class Client {

    @Id
    private Long id;
    @Column("firstName")
    private String firstName;
    @Column("lastName")
    private String lastName;
    @Column("email")
    private String email;
    @Column("password")
    private String password;
    @Column("contactNum")
    private String contactNum;
    @Column(value = "role")
    private String role;
    @Column(value = "isAccountNonExpired")
    private String isAccountNonExpired;
    @Column(value = "isAccountNonLocked")
    private String isAccountNonLocked;
    @Column(value = "isCredentialsNonExpired")
    private String isCredentialsNonExpired;
    @Column(value = "isEnabled")
    private String isEnabled;

    public Client() {
    }

    public Client(
            String firstName,
            String lastName,
            String email,
            String password,
            String contactNum,
            String role
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contactNum = contactNum;
        this.role = role;
        this.isAccountNonExpired = "TRUE";
        this.isAccountNonLocked = "TRUE";
        this.isEnabled = "TRUE";
        this.isCredentialsNonExpired = "TRUE";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIsAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setIsAccountNonExpired(String isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    public String getIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setIsAccountNonLocked(String isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public String getIsCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setIsCredentialsNonExpired(String isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }
}
