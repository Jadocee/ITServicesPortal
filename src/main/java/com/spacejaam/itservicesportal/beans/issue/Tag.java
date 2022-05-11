package com.spacejaam.itservicesportal.beans.issue;

/**
 *
 */
public enum Tag {
    WAITING_TP("Waiting on third-party"), WAITING("Waiting on reporter"), ARTICLE("Knowledge-Base Article");

    private final String label;

    Tag(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
