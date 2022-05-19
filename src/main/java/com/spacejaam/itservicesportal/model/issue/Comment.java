package com.spacejaam.itservicesportal.model.issue;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table(value = "Comment")
public class Comment {
    @Id
    @Column(value = "id")
    private Long id;
    @Column(value = "message")
    private String message;
    @Column(value = "date")
    private Date created;

    public Comment() {
    }

    public Comment(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    private void setCreated(Date created) {
        this.created = created;
    }
}
