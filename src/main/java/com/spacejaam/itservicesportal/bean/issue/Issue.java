package com.spacejaam.itservicesportal.bean.issue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;

/**
 *
 */
public class Issue implements Serializable {

  private static String title;
  private static String desc;
  private static Category category;
  private String author;
  private State state;
  private EnumSet<Tag> tags;
  private Date reported;
  private ArrayList<Comment> comments; // TODO might be redundant

  public Issue() {
  }

  public static String getTitle() {
    return title;
  }

  public static String getDescription() {
    return desc;
  }

  public static Category getCatagory() {
    return category;
  }
}
