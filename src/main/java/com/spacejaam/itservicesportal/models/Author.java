package com.spacejaam.itservicesportal.models;

import java.io.Serializable;

public class Author implements Serializable {
    private final String displayName;
    private final String role;
    private final Long id;

    public Author(String displayName, String role, Long id) {
        this.displayName = displayName;
        this.role = role;
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }
}
