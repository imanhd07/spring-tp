package com.example.productsapi.controller;

import com.example.productsapi.model.Article;
import com.example.productsapi.model.Product;
import com.example.productsapi.model.User;
import com.example.productsapi.repository.UserRepository;
import com.example.productsapi.service.ArticleService;
import com.example.productsapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Login Page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Register Page
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "redirect:/login";
    }

    // PRODUITS
    @GetMapping("/")
    public String products(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("newProduct", new Product());
        return "index";
    }

    @PostMapping("/products/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
        model.addAttribute("product", product);
        return "product-edit";
    }

    @PostMapping("/products/update")
    public String updateProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/";
    }

    // ARTICLES
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

    @GetMapping("/articles/edit/{id}")
    public String editArticle(@PathVariable Long id, Model model) {
        Article article = articleService.findById(id)
                .orElseThrow(() -> new RuntimeException("Article introuvable"));
        model.addAttribute("article", article);
        return "article-edit";
    }

    @PostMapping("/articles/update")
    public String updateArticle(@ModelAttribute Article article) {
        articleService.save(article);
        return "redirect:/articles";
    }

    @GetMapping("/articles/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteById(id);
        return "redirect:/articles";
    }
}