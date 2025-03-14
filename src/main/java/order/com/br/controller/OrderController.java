package order.com.br.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import order.com.br.model.Order;
import order.com.br.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@Tag(name = "Order", description = "Endpoints for managing order")
public class OrderController {

    @Autowired
    OrderService service;

    @GetMapping("/")
    @Operation(summary = "Finds all orders", description = "Finds all orders", tags = {"Order"})
    public Page<Order> findAll(@PageableDefault(sort = {"orderDate"})Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find one order", description = "Find one order", tags = {"Order"})
    public Order findById(@PathVariable(value = "id") UUID id) {
        return service.findById(id);
    }

    @PostMapping("/")
    @Operation(summary = "Create one order", description = "Create one order", tags = {"Order"})
    public Order create(@RequestBody Order order) {
        return service.create(order);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update one order", description = "Update one order", tags = {"Order"})
    public Order update(@PathVariable(value = "id") UUID id, @RequestBody Order order) {
        return service.update(id, order);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete one order", description = "Delete one order", tags = {"Order"})
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
