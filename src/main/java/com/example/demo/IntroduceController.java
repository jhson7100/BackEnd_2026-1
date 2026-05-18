package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class IntroduceController {

    private final Map<Long, Article> articles = new HashMap<>();
    private long nextId = 1;

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable Long id) {
        return findArticle(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Article createArticle(@RequestBody ArticleRequest request) {
        Article article = new Article(nextId++, request.description());
        articles.put(article.id(), article);

        return article;
    }

    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody ArticleRequest request) {
        findArticle(id);

        Article article = new Article(id, request.description());
        articles.put(id, article);

        return article;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        findArticle(id);
        articles.remove(id);
    }

    private Article findArticle(Long id) {
        Article article = articles.get(id);

        if (article == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return article;
    }

    record Article(Long id, String description) {
    }

    record ArticleRequest(String description) {
    }
}