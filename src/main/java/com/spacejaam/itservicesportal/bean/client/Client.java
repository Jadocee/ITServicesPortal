package com.spacejaam.itservicesportal.bean.client;

import javax.annotation.PostConstruct;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

//@Component("clientBean")
//@Scope(value = "session")
@Table(name = "clients")
public class Client {

  @Id
  private Long id;
  //  @Version
//  private Integer version;
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
  private Role role;

  public Client() {
  }

  public Client(String firstName, String lastName, String email, String contactNum) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.contactNum = contactNum;
    this.role = Role.USER;
  }

  public Client(String firstName, String lastName, String email, String contactNum, Role role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.contactNum = contactNum;
    this.role = role;
  }

  @PostConstruct
  public void init(String firstName, String lastName, String email, String contactNum, Role role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.contactNum = contactNum;
    this.role = role;
  }

  @Column("role")
  public String getRoleLabel() {
    return this.role.getLabel();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  public String getContactNum() {
    return contactNum;
  }

  public void setContactNum(String contactNum) {
    this.contactNum = contactNum;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

}
