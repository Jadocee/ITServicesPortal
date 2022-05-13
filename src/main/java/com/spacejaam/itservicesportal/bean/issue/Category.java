package com.spacejaam.itservicesportal.bean.issue;

/**
 *
 */
public enum Category {
  CONNECTION("Can't Connect", BaseCategory.NETWORK), SPEED("Speed", BaseCategory.NETWORK),
  DROPOUTS("Constant Dropout", BaseCategory.NETWORK), SLOW("Slow to load", BaseCategory.SOFTWARE),
  WONTLOAD("Won't load at all", BaseCategory.SOFTWARE), POWER("Computer won't turn on",
      BaseCategory.HARDWARE),
  BLUESCREEN("Computer \"Blue screens\"", BaseCategory.HARDWARE), DRIVE("Disk Drive",
      BaseCategory.HARDWARE),
  PERIPHERALS("Peripherals", BaseCategory.HARDWARE), SEND("Can't send", BaseCategory.EMAIL),
  RECEIVE("Can't receive", BaseCategory.EMAIL), SPAM("SPAM/Phishing", BaseCategory.EMAIL),
  PASSRESET("Password Reset", BaseCategory.ACCOUNT), DETAILS("Wrong details", BaseCategory.ACCOUNT);

  private final BaseCategory category;
  private final String label;

  Category(String label, BaseCategory category) {
    this.label = label;
    this.category = category;
  }

  public String getSubCategory() {
    return this.label;
  }

  public String getCategory() {
    return this.category.getLabel();
  }

  private enum BaseCategory {
    NETWORK("Network"), SOFTWARE("Software"), HARDWARE("Hardware"), EMAIL("Email"), ACCOUNT(
        "Account");

    private final String label;

    BaseCategory(String label) {
      this.label = label;
    }

    public String getLabel() {
      return this.label;
    }
  }

}
