package com.example.productsapi;

import com.example.productsapi.model.Article;
import com.example.productsapi.model.Product;
import com.example.productsapi.model.User;
import com.example.productsapi.repository.ArticleRepository;
import com.example.productsapi.repository.ProductRepository;
import com.example.productsapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private UserRepository userRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private ArticleRepository articleRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        // user
        if (userRepository.count() == 0) {
            User user = new User();
            user.setName("Admin");
            user.setEmail("admin@test.com");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setRole("ROLE_USER");
            userRepository.save(user);
        }

        // Products
        if (productRepository.count() == 0) {
            Product p1 = new Product();
            p1.setName("Stylo");
            p1.setPrice(10.0);
            p1.setDescription("Stylo bleu");
            productRepository.save(p1);

            Product p2 = new Product();
            p2.setName("Cahier");
            p2.setPrice(25.0);
            p2.setDescription("Cahier 100 pages");
            productRepository.save(p2);

            Product p3 = new Product();
            p3.setName("Sac");
            p3.setPrice(150.0);
            p3.setDescription("Sac à dos");
            productRepository.save(p3);
        }

        // Articles
        if (articleRepository.count() == 0) {
            Article a1 = new Article();
            a1.setTitle("Introduction à Spring Boot");
            a1.setContent("Spring Boot est un framework Java qui simplifie le développement d'applications.");
            articleRepository.save(a1);

            Article a2 = new Article();
            a2.setTitle("Sécurité avec JWT");
            a2.setContent("JWT est un standard pour sécuriser les APIs REST avec des tokens.");
            articleRepository.save(a2);
        }
    }
}