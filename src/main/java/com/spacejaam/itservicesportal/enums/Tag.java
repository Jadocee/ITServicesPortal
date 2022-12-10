package com.spacejaam.itservicesportal.enums;

public enum Tag {
    WAITING_TP("Waiting on third-party"), WAITING("Waiting on reporter"), ARTICLE(
            "Knowledge-Base Article");

    private final String label;

    Tag(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
