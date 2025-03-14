package order.com.br.service;

import order.com.br.exception.ResourceNotFoundException;
import order.com.br.model.Product;
import order.com.br.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    private ProductRepository repository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        logger.info("Finding all product.");
        return repository.findAll(pageable);
    }

    @Override
    public Product findById(UUID id) {
        logger.info("Finding a product.");
        return repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(("No record found for this ID!")));
    }

    @Override
    public Product create(Product product) {
        logger.info("Creating a product.");
        return repository.save(product);
    }

    @Override
    public Product update(UUID id, Product product) {
        logger.info("Updating a product.");
        var entity = repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(("No record found for this ID!")));
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setStockQuantity(product.getStockQuantity());

        return repository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        logger.info("Deleting a product.");
        var entity = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("No record found for this ID!")));
        repository.delete(entity);
    }
}
