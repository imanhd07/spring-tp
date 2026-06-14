package com.example.productsapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import com.example.productsapi.model.Product;
import com.example.productsapi.repository.ProductRepository;
import com.example.productsapi.service.ProductService;
import java.util.List;

class ProductServiceTest {

    @Test
    void shouldReturnAllProducts() {
        ProductRepository repository = mock(ProductRepository.class);
        ProductService service = new ProductService(repository);

        Product p = new Product();
        p.setName("Stylo");
        p.setPrice(10.0);

        when(repository.findAll()).thenReturn(List.of(p));

        List<Product> result = service.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Stylo");
        verify(repository).findAll();
    }

    @Test
    void shouldSaveProduct() {
        ProductRepository repository = mock(ProductRepository.class);
        ProductService service = new ProductService(repository);

        Product product = new Product();
        product.setName("Cahier");
        product.setPrice(5.0);

        Product saved = new Product();
        saved.setId(1L);
        saved.setName("Cahier");
        saved.setPrice(5.0);

        when(repository.save(product)).thenReturn(saved);

        Product result = service.save(product);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Cahier");
        verify(repository).save(product);
    }
}