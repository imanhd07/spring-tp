package com.example.productsapi.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

@Entity
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments;
}