package order.com.br.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import order.com.br.model.Product;
import order.com.br.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/product")
@Tag(name = "Product", description = "Endpoints for managing product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    @Operation(summary = "Finds all products", description = "Finds all products", tags = {"Product"})
    public Page<Product> findAll(@PageableDefault(sort = {"name"}) Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find one product", description = "Find one product", tags = {"Product"})
    public Product findById(@PathVariable(value = "id") UUID id) {
        return service.findById(id);
    }

    @PostMapping("/")
    @Operation(summary = "Create one product", description = "Create one product", tags = {"Product"})
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update one product", description = "Update one product", tags = {"Product"})
    public Product update(@PathVariable(value = "id") UUID id, @RequestBody Product product) {
        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting one cutomer", description = "Deleting one cutomer", tags = {"Product"})
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
