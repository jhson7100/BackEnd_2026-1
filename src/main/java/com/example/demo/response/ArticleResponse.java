package com.example.demo.response;

import com.example.demo.model.Article;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

@JsonPropertyOrder({"title", "author", "content", "date"})
public class ArticleResponse {

    private String title;
    private String author;
    private String content;
    private LocalDateTime date;

    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.author = article.getAuthor();
        this.content = article.getContent();
        this.date = article.getDate();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDate() {
        return date;
    }
}