package edu.bo.uyunicode.ai.mcp.server.services;

import edu.bo.uyunicode.ai.mcp.server.entities.Product;
import edu.bo.uyunicode.ai.mcp.server.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductService {
    private final IProductRepository productRepository;

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public Product update(Product product) {
        return this.productRepository.findById(product.getProductId())
                .map(this.productRepository::save)
                .orElse(null);
    }
}
