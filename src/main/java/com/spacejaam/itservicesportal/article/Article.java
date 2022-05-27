package com.spacejaam.itservicesportal.article;

import com.spacejaam.itservicesportal.issue.Category;
import com.spacejaam.itservicesportal.issue.State;
import com.spacejaam.itservicesportal.issue.SubCategory;
import com.spacejaam.itservicesportal.issue.Tag;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Set;

public class Article implements Serializable {
    private final String title;
    private final String desc;
    private final State state;
    private final Category category;
    private final SubCategory subCategory;
    private final LocalDate addedOn;
    private final String solution;
    private final LocalDate solutionProvidedOn;
    @Id
    private final Long id;
    private LocalDate resolvedOn;
    private Set<Tag> tags;

    public Article(
            Long id,
            String title,
            String desc,
            State state,
            Category category,
            SubCategory subCategory,
            LocalDate addedOn,
            String solution,
            LocalDate solutionProvidedOn
    ) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.state = state;
        this.category = category;
        this.subCategory = subCategory;
        this.addedOn = addedOn;
        this.solution = solution;
        this.solutionProvidedOn = solutionProvidedOn;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
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

    public String getState() {
        return state.toString();
    }

    public String getCategory() {
        return category.toString();
    }

    public String getSubCategory() {
        return subCategory.toString();
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

    public String getAddedOn() {
        return addedOn.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    public String getSolution() {
        return solution;
    }

    public String getSolutionProvidedOn() {
        return solutionProvidedOn.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
}
