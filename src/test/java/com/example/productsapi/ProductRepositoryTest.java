package com.example.productsapi;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.example.productsapi.model.Product;
import com.example.productsapi.repository.ProductRepository;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    void shouldSaveProduct() {
        Product product = new Product();
        product.setName("Casque");
        product.setPrice(79.99);

        Product saved = repository.save(product);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Casque");
    }

    @Test
    void shouldFindAllProducts() {
        Product p = new Product();
        p.setName("Micro");
        p.setPrice(39.99);
        repository.save(p);

        assertThat(repository.findAll()).hasSize(1);
    }
}