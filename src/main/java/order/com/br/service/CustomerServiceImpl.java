package order.com.br.service;

import order.com.br.exception.ResourceNotFoundException;
import order.com.br.model.Customer;
import order.com.br.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Logger logger = Logger.getLogger(CustomerServiceImpl.class.getName());

    @Autowired
    private CustomerRepository repository;

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        logger.info("Finding all customer.");
        return repository.findAll(pageable);
    }

    @Override
    public Customer findById(UUID id) {
        logger.info("Finding a customer.");
        return repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(("No record found for this ID!")));
    }

    @Override
    public Customer create(Customer customer) {
        logger.info("Creating a customer.");
        return repository.save(customer);
    }

    @Override
    public Customer update(UUID id, Customer customer) {
        logger.info("Updating a customer.");
        var entity = repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(("No record found for this ID!")));
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());
        entity.setPhone(customer.getPhone());

        return repository.save(entity);
    }

    @Override
    public void delete(UUID id) {
        logger.info("Deleting a customer.");
        var entity = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("No record found for this ID!")));
        repository.delete(entity);
    }
}
