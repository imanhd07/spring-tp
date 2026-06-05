package com.example.productsapi.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;
    private List<CommentResponse> comments;
}