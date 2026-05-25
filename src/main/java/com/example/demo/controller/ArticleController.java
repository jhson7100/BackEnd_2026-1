package com.example.demo.controller;

import com.example.demo.request.ArticleRequest;
import com.example.demo.response.ArticleResponse;
import com.example.demo.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("boardName", "자유게시판");
        model.addAttribute("articles", articleService.getArticles());

        return "posts";
    }

    @ResponseBody
    @GetMapping("/articles")
    public List<ArticleResponse> getArticles() {
        return articleService.getArticles()
                .stream()
                .map(ArticleResponse::new)
                .toList();
    }

    @ResponseBody
    @GetMapping("/articles/{id}")
    public ArticleResponse getArticle(@PathVariable Long id) {
        try {
            return new ArticleResponse(articleService.getArticle(id));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/articles")
    public ArticleResponse createArticle(@RequestBody ArticleRequest request) {
        return new ArticleResponse(articleService.createArticle(request));
    }

    @ResponseBody
    @PutMapping("/articles/{id}")
    public ArticleResponse updateArticle(
            @PathVariable Long id,
            @RequestBody ArticleRequest request
    ) {
        try {
            return new ArticleResponse(articleService.updateArticle(id, request));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable Long id) {
        try {
            articleService.deleteArticle(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}