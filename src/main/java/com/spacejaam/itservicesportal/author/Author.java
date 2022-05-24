package com.spacejaam.itservicesportal.author;

import java.io.Serializable;

public class Author implements Serializable {
    private final String displayName;
    private final String role;

    public Author(String displayName, String role) {
        this.displayName = displayName;
        this.role = role;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getRole() {
        return role;
    }
}
