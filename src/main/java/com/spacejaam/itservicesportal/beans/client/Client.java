package com.spacejaam.itservicesportal.beans.client;

import java.io.Serializable;
import org.springframework.stereotype.Component;

@Component
public class Client implements Serializable {

//    private static final long serialVersionIUID = 1ac6dcc5-002f-47ae-a8ce-154ada362770;

  private String firstName;
  private String lastName;
  private String email;
  // private String password;
  private String contactNum;
  private Role role;

  public Client() {
  }

  public void init(String firstName, String lastName, String email, String contactNum, Role role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.contactNum = contactNum;
    this.role = role;
  }

}
