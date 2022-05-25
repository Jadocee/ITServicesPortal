package com.spacejaam.itservicesportal.comment;

import com.spacejaam.itservicesportal.author.Author;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Comment implements Serializable {
    @Id
    private Long id;
    private String message;
    private Author author;
    private LocalDateTime created;
    private Boolean recommended;

    public Comment(Long id, String message, LocalDateTime created, Author author) {
        this.id = id;
        this.message = message;
        this.created = created;
        this.author = author;
        this.recommended = false;
    }

    public Comment(Long id, String message, LocalDateTime created, Author author, Boolean recommended) {
        this.id = id;
        this.message = message;
        this.created = created;
        this.author = author;
        this.recommended = recommended;
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
        return created.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        // TODO: convert from UTC to local timezone
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Boolean getRecommended() {
        return recommended;
    }
}
