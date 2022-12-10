package com.spacejaam.itservicesportal.enums;

public enum State {
    NEW("New"), PROGRESS("In Progress"), COMPLETE("Completed"), RESOLVED("Resolved");

    private final String label;

    State(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
