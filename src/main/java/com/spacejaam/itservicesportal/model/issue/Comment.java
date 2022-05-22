package com.spacejaam.itservicesportal.model.issue;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Table(value = "Comment")
public class Comment {
    @Id
    private Long id;
    private String message;
    private String author;
    private LocalDateTime created;

    public Comment(Long id, String message, LocalDateTime created, String author) {
        this.id = id;
        this.message = message;
        this.created = created;
        this.author = author;
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

    public String getCreated() {
        return created.atZone(ZoneId.of("Australia/NSW")).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
