package com.example.demo.repository;

import com.example.demo.model.Article;
import com.example.demo.request.ArticleRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ArticleRepository {

    private final Map<Long, Article> articles = new LinkedHashMap<>();
    private long nextId = 1;

    @PostConstruct
    public void init() {
        LocalDateTime date = LocalDateTime.of(2024, 5, 14, 9, 14, 27, 610000000);

        articles.put(nextId, new Article(nextId++, "제목0", "회원0", date, ""));
        articles.put(nextId, new Article(nextId++, "제목1", "회원1", date, "내용입니다!!"));
        articles.put(nextId, new Article(nextId++, "제목2", "회원2", date, "내용입니다!!내용입니다!!"));
        articles.put(nextId, new Article(nextId++, "제목3", "회원3", date, "내용입니다!!내용입니다!!내용입니다!!"));
    }

    public Collection<Article> findAll() {
        return articles.values();
    }

    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(articles.get(id));
    }

    public Article save(ArticleRequest request) {
        Article article = new Article(
                nextId++,
                request.getTitle(),
                request.getAuthor(),
                LocalDateTime.now(),
                request.getContent()
        );

        articles.put(article.getId(), article);
        return article;
    }

    public Article update(Long id, ArticleRequest request) {
        Article article = articles.get(id);
        article.update(request.getTitle(), request.getAuthor(), request.getContent());

        return article;
    }

    public void delete(Long id) {
        articles.remove(id);
    }
}