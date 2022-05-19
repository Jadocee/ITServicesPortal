package com.spacejaam.itservicesportal.model.issue;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.EnumSet;

@Table(value = "Issue")
public class Issue {
    @Id
    @Column(value = "id")
    private Long id;
    @Column(value = "title")
    private String title;
    @Column(value = "desc")
    private String desc;
    @Column(value = "category")
    private Category category;
    @Column(value = "subcategory")
    private SubCategory subCategory;
    @Column(value = "author")
    private Long author;
    @Column(value = "state")
    private State state;
    @Column(value = "tags")
    private EnumSet<Tag> tags;
    @Column(value = "date")
    private Date reported;

    public Issue() {
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    private void setDesc(String desc) {
        this.desc = desc;
    }

    public Category getCategory() {
        return category;
    }

    private void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    private void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Long getAuthor() {
        return author;
    }

    private void setAuthor(Long author) {
        this.author = author;
    }

    public State getState() {
        return state;
    }

    private void setState(State state) {
        this.state = state;
    }

    public EnumSet<Tag> getTags() {
        return tags;
    }

    private void setTags(EnumSet<Tag> tags) {
        this.tags = tags;
    }

    public boolean addTag(Tag tag) {
        return this.tags.add(tag);
    }

    public boolean removeTag(Tag tag) {
        return this.tags.remove(tag);
    }

    public Date getReported() {
        return reported;
    }

    private void setReported(Date reported) {
        this.reported = reported;
    }
}
