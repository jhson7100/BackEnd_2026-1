package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IntroduceController {

    private final Map<Long, Article> articles = new HashMap<>();
    private long nextId = 1;

    @PostMapping("/article")
    @ResponseStatus(HttpStatus.CREATED)
    public Article createArticle(@RequestBody ArticleRequest request) {
        Article article = new Article(nextId++, request.description());
        articles.put(article.id(), article);
        return article;
    }

    @GetMapping("/article/{id}")
    public Article getArticle(@PathVariable Long id) {
        Article article = articles.get(id);

        if (article == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return article;
    }

    @PutMapping("/article/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody ArticleRequest request) {
        if (!articles.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Article article = new Article(id, request.description());
        articles.put(id, article);
        return article;
    }

    @DeleteMapping("/article/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable Long id) {
        if (articles.remove(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    record Article(Long id, String description) {
    }

    record ArticleRequest(String description) {
    }
}