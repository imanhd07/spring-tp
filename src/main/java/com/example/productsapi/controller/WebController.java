package com.example.productsapi.controller;

import com.example.productsapi.model.Article;
import com.example.productsapi.model.Product;
import com.example.productsapi.service.ArticleService;
import com.example.productsapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("newProduct", new Product());
        return "index";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/";
    }

    @GetMapping("/articles")
    public String articles(Model model) {
        model.addAttribute("articles", articleService.findAll());
        model.addAttribute("newArticle", new Article());
        return "articles";
    }

    @PostMapping("/articles/add")
    public String addArticle(@ModelAttribute Article article) {
        articleService.save(article);
        return "redirect:/articles";
    }
    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("newProduct", new Product());
        return "index";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}