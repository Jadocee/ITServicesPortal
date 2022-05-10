package com.spacejaam.itservicesportal.model.client;

import java.io.Serializable;

/**
 *
 */
public class Client implements Serializable {

    private final String firstName;
    private final String lastName;
    private final String email;
    // private String password;
    private final String contactNum;
    private final Role role;

    public Client(String firstName, String lastName, String email, String contactNum, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNum = contactNum;
        this.role = role;
    }

}
