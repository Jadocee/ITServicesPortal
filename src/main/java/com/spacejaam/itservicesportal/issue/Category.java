package com.spacejaam.itservicesportal.issue;

/**
 *
 */
public enum Category {
    NETWORK("Network"), SOFTWARE("Software"), HARDWARE("Hardware"), EMAIL("Email"), ACCOUNT("Account");

    private final String label;

    Category(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
