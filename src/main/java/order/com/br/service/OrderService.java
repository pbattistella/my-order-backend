package order.com.br.service;

import order.com.br.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderService {

    public Page<Order> findAll(Pageable pageable);
    public Order findById(UUID id);
    public Order create(Order order);
    public Order update(UUID id, Order order);
    public void delete(UUID id);

}
