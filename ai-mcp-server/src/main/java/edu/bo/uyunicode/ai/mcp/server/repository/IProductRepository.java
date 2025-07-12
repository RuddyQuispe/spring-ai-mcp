package edu.bo.uyunicode.ai.mcp.server.repository;

import edu.bo.uyunicode.ai.mcp.server.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
}
