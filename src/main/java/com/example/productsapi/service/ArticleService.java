package com.example.productsapi.service;

import com.example.productsapi.model.Article;
import com.example.productsapi.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }
}