package com.spacejaam.itservicesportal.bean.client;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("clientBean")
@Scope(value = "session")
public class Client implements Serializable {
  private long id;
  private String firstName;
  private String lastName;
  private String email;
  // private String password;
  private String contactNum;
  private Role role;

  public Client() {
  }

  @PostConstruct
  public void init(String firstName, String lastName, String email, String contactNum, Role role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.contactNum = contactNum;
    this.role = role;
  }

  public String getFirstName() {
    return firstName;
  }

  public Role getRole() {
    return role;
  }

}
