package com.example.productsapi.service;

import com.example.productsapi.model.Article;
import com.example.productsapi.model.Comment;
import com.example.productsapi.repository.ArticleRepository;
import com.example.productsapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public Comment addComment(Long articleId, Comment comment) {
        Article article = articleRepository.findById(articleId)
            .orElseThrow(() -> new RuntimeException("Article non trouvé"));
        comment.setArticle(article);
        return commentRepository.save(comment);
    }
}