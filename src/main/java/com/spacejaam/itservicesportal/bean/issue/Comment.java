package com.spacejaam.itservicesportal.bean.issue;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class Comment implements Serializable {

  private final String author;
  private final Date created;
  private final String msg;

  public Comment(String author, Date created, String msg) {
    this.author = author;
    this.created = created;
    this.msg = msg;
  }
}
