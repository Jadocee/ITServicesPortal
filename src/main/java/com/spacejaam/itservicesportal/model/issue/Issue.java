package com.spacejaam.itservicesportal.model.issue;

import org.springframework.data.annotation.Id;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

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
    private LocalDateTime createdOn;

    public Issue(String title, String desc, Category category, SubCategory subCategory) {
        this.title = title;
        this.desc = desc;
        this.category = category;
        this.subCategory = subCategory;
    }

    public Issue(Long id, String title, String desc, Category category, SubCategory subCategory, String author, State state, LocalDateTime createdOn) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.category = category;
        this.subCategory = subCategory;
        this.author = author;
        this.state = state;
        this.createdOn = createdOn;
        this.tags = new HashSet<>();
    }

    public Issue() {
    }

    public ArrayList<String> getTags() {
        if (this.tags.isEmpty()) {
            return null;
        }

        final ArrayList<String> arrayList = new ArrayList<>();
        for (final Tag tag : this.tags) {
            arrayList.add(tag.toString());
        }
        return arrayList;
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCategory() {
        return category.toString();
    }

    public Category category() {
        return category;
    }

    public String getSubCategory() {
        return subCategory.toString();
    }

    public SubCategory subCategory() {
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

    public String getState() {
        return state.toString();
    }

    public State state() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getCreatedOn() {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        format.setTimeZone(TimeZone.getTimeZone("AEST"));
//        return createdOn.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        return format.format(createdOn);
        // TODO: offset by local timezone
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
}
