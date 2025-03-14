package order.com.br.service;

import order.com.br.model.Customer;
import order.com.br.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CustomerService {
    public Page<Customer> findAll(Pageable pageable);
    public Customer findById(UUID id);
    public Customer create(Customer customer);
    public Customer update(UUID id, Customer customer);
    public void delete(UUID id);
}
