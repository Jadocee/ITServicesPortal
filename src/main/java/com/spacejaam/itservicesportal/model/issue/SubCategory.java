package com.spacejaam.itservicesportal.model.issue;


public enum SubCategory {
    CONNECTION("Can't Connect"), SPEED("Speed"),
    DROPOUTS("Constant Dropout"), SLOW("Slow to load"),
    WONTLOAD("Won't load at all"), POWER("Computer won't turn on"),
    BLUESCREEN("Computer \"Blue screens\""), DRIVE("Disk Drive"), PERIPHERALS("Peripherals"), SEND("Can't send"),
    RECEIVE("Can't receive"), SPAM("SPAM/Phishing"),
    PASSRESET("Password Reset"), DETAILS("Wrong details");

    private final String label;

    SubCategory(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
