package order.com.br.service;

import order.com.br.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import java.util.UUID;

public interface ProductService {
    public Page<Product> findAll(Pageable pageable);
    public Product findById(UUID id);
    public Product create(Product product);
    public Product update(UUID id, Product product);
    public void delete(UUID id);
}
