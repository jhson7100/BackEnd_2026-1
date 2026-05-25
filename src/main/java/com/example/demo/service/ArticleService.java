package com.example.demo.service;

import com.example.demo.model.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.request.ArticleRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Collection<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Article getArticle(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public Article createArticle(ArticleRequest request) {
        return articleRepository.save(request);
    }

    public Article updateArticle(Long id, ArticleRequest request) {
        getArticle(id);
        return articleRepository.update(id, request);
    }

    public void deleteArticle(Long id) {
        getArticle(id);
        articleRepository.delete(id);
    }
}