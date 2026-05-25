package com.example.demo.model;

import java.time.OffsetDateTime;

public class Article {

    private Long id;
    private String title;
    private String author;
    private OffsetDateTime date;
    private String content;

    public Article(Long id, String title, String author, OffsetDateTime date, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public void update(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
}