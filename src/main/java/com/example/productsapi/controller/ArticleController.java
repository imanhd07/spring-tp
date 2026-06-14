package com.example.productsapi.controller;

import com.example.productsapi.dto.*;
import com.example.productsapi.model.Article;
import com.example.productsapi.model.Comment;
import com.example.productsapi.service.ArticleService;
import com.example.productsapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired private ArticleService articleService;
    @Autowired private CommentService commentService;

    private ArticleResponse toResponse(Article article) {
        List<CommentResponse> comments = article.getComments() == null
                ? Collections.emptyList()
                : article.getComments().stream()
                .map(c -> new CommentResponse(c.getId(), c.getText(), c.getAuthor()))
                .collect(Collectors.toList());
        return new ArticleResponse(article.getId(), article.getTitle(), article.getContent(), comments);
    }

    @GetMapping
    public List<ArticleResponse> getAll() {
        return articleService.findAll().stream()
                .map(this::toResponse).collect(Collectors.toList());
    }

    @PostMapping
    public ArticleResponse create(@RequestBody ArticleRequest request) {
        Article article = new Article();
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
        return toResponse(articleService.save(article));
    }

    @GetMapping("/{id}")
    public ArticleResponse getById(@PathVariable Long id) {
        return toResponse(articleService.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found")));
    }

    @PostMapping("/{id}/comments")
    public CommentResponse addComment(@PathVariable Long id,
                                      @RequestBody CommentRequest request) {
        Comment comment = new Comment();
        comment.setText(request.getText());
        comment.setAuthor(request.getAuthor());
        Comment saved = commentService.addComment(id, comment);
        return new CommentResponse(saved.getId(), saved.getText(), saved.getAuthor());
    }
}