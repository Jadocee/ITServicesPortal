package com.spacejaam.itservicesportal.dao.performance;


import com.spacejaam.itservicesportal.model.issue.Category;

public class UnresolvedCount {
    private final Category category;
    private final Integer count;

    public UnresolvedCount(Category category, Integer count) {
        this.category = category;
        this.count = count;
    }

    public String getCategory() {
        return category.toString();
    }

    public String getCount() {
        return count.toString();
    }
}
