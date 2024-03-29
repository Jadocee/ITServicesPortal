package com.spacejaam.itservicesportal.models;

import com.spacejaam.itservicesportal.enums.Category;
import com.spacejaam.itservicesportal.enums.State;
import com.spacejaam.itservicesportal.enums.SubCategory;
import com.spacejaam.itservicesportal.enums.Tag;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Issue implements Serializable {
    private final String desc;
    private final SubCategory subCategory;
    @Id
    private Long id;
    private String title;
    private Category category;
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
        try {
            final ArrayList<String> arrayList = new ArrayList<>();
            for (final Tag tag : this.tags) {
                arrayList.add(tag.toString());
            }
            return arrayList;
        } catch (NullPointerException e) {
            return null;
        }
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

    public String getCategory() {
        return category.toString();
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public void setState(State state) {
        this.state = state;
    }

    public State state() {
        return state;
    }

    public String getCreatedOn() {
        return createdOn.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        // TODO: offset by local timezone
    }

    public String getResolvedOn() {
        try {
            return resolvedOn.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        } catch (NullPointerException e) {
            return null;
        }
    }

    public void setResolvedOn(LocalDate resolvedOn) {
        this.resolvedOn = resolvedOn;
    }
}


