package com.spacejaam.itservicesportal.model.issue;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Set;

public class Issue {
    @Id
    private Long id;
    private String title;
    private String desc;
    private Category category;
    private SubCategory subCategory;
    private String author;
    private State state;
    private Set<Tag> tags;
    private Date createdOn;

    public Issue(String title, String desc, Category category, SubCategory subCategory) {
        this.title = title;
        this.desc = desc;
        this.category = category;
        this.subCategory = subCategory;
    }

    public Issue(Long id, String title, String desc, Category category, SubCategory subCategory, String author, State state, Date createdOn) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.category = category;
        this.subCategory = subCategory;
        this.author = author;
        this.state = state;
        this.createdOn = createdOn;
    }

    public Issue() {
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
