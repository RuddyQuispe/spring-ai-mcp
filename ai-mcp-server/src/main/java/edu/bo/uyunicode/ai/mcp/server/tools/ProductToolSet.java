package edu.bo.uyunicode.ai.mcp.server.tools;

import edu.bo.uyunicode.ai.mcp.server.entities.Product;
import edu.bo.uyunicode.ai.mcp.server.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;


@Component
@RequiredArgsConstructor
public class ProductToolSet {

    private final Logger LOGGER = Logger.getLogger(ProductToolSet.class.getName());
    private final ProductService productService;

    @Tool(name = "greet", description = "greet the user")
    public String greet(@ToolParam(description = "nombre", required = true) String userName) {
        String sessionId = UUID.randomUUID().toString();
        LOGGER.info("[%s]: Greeting the user %s".formatted(sessionId, userName));
        return "Hola, como estas %s, te saluda el servidor MCP [%s]".formatted(userName, sessionId);
    }

    @Tool(name = "findAllProducts", description = "get all available products")
    public String findAll() {
        String sessionId = UUID.randomUUID().toString();
        LOGGER.info("[%s]: Obtaining all available products".formatted(sessionId));
        List<Product> products = this.productService.findAll();
        StringBuilder result = new StringBuilder("Los productos disponibles son: \n");
        products.forEach(p -> result.append("nombre: " + p.getName() + ", descripcion: " + p.getDescripcion() + ", precio: " + p.getPrice()));
        return result.toString();

    }

    @Tool(name = "findProductByName", description = "get product by name")
    public String findByName(@ToolParam(description = "nombre", required = true) String name) {
        String sessionId = UUID.randomUUID().toString();
        LOGGER.info("[%s]: Get product by name %s".formatted(sessionId, name));
        Optional<Product> product = this.productService.findByName(name);
        return product.map(p -> "Producto\n" +
                "nombre: " + p.getName() + ", descripcion: " + p.getDescripcion() + ", precio: " + p.getPrice()
        ).orElse("No existe producto con nombre: " + name);
    }

    @Tool(name = "saveProduct", description = "Save a new product where the name, description, and price of the new product are required")
    public String findByName(@ToolParam(description = "nombre", required = true) String name,
                             @ToolParam(description = "description", required = true) String description,
                             @ToolParam(description = "price", required = true) BigDecimal price) {
        String sessionId = UUID.randomUUID().toString();
        LOGGER.info("[%s]: Save new product %s | %s | %s".formatted(sessionId, name, description, price));
        Product product = this.productService.save(new Product(null, name, description, price));
        return "producto guardado correctamente";
    }
}
