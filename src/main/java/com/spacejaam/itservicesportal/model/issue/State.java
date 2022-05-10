package com.spacejaam.itservicesportal.model.issue;

/**
 *
 */
public enum State {
    NEW("New"), PROGRESS("In Progress"), COMPLETE("Completed"), RESOLVED("Resolved");

    private final String label;

    State(String label) {
        this.label = label;
    }
}
