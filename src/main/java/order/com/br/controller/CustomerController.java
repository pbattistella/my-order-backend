package order.com.br.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import order.com.br.model.Customer;
import order.com.br.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
@Tag(name = "Customer", description = "Endpoints for managing customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/")
    @Operation(summary = "Finds all customers", description = "Finds all customers", tags = {"Customer"})
    public Page<Customer> findAll(@PageableDefault(sort = {"name"}) Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find one customer", description = "Find one customer", tags = {"Customer"})
    public Customer findById(@PathVariable(value = "id") UUID id) {
        return service.findById(id);
    }

    @PostMapping("/")
    @Operation(summary = "Create one customer", description = "Create one customer", tags = {"Customer"})
    public Customer create(@RequestBody Customer customer) {
        return service.create(customer);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update one customer", description = "Update one customer", tags = {"Customer"})
    public Customer update(@PathVariable(value = "id") UUID id, @RequestBody Customer customer) {
        return service.update(id, customer);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting one cutomer", description = "Deleting one cutomer", tags = {"Customer"})
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
