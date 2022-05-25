package com.spacejaam.itservicesportal.issue;

import com.spacejaam.itservicesportal.author.Author;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Issue implements Serializable {
    @Id
    private Long id;
    private String title;
    private final String desc;
    private Category category;
    private final SubCategory subCategory;
    private Author author;
    private State state;
    private Set<Tag> tags;
    private LocalDateTime createdOn;
    private LocalDate resolvedOn;

    public Issue(String title, String desc, Category category, SubCategory subCategory) {
        this.title = title;
        this.desc = desc;
        this.category = category;
        this.subCategory = subCategory;
    }

    public Issue(
            Long id,
            String title,
            String desc,
            Category category,
            SubCategory subCategory,
            Author author,
            State state,
            LocalDateTime createdOn
    ) {
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

    public Author getAuthor() {
        return author;
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
        return createdOn.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        // TODO: offset by local timezone
    }

    public LocalDate getResolvedOn() {
        return resolvedOn;
    }

    public void setResolvedOn(LocalDate resolvedOn) {
        this.resolvedOn = resolvedOn;
    }
}


