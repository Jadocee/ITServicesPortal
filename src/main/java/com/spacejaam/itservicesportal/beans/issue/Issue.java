package com.spacejaam.itservicesportal.beans.issue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;

/**
 *
 */
public class Issue implements Serializable {

    private String title;
    private String desc;
    private String author;
    private State state;
    private Category category;
    private EnumSet<Tag> tags;
    private Date reported;
    private ArrayList<Comment> comments; // TODO might be redundant

    public Issue() {
    }

}
