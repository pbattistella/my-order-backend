package order.com.br.service;

import order.com.br.exception.ResourceNotFoundException;
import order.com.br.model.Customer;
import order.com.br.model.Item;
import order.com.br.model.Order;
import order.com.br.model.Product;
import order.com.br.repository.ItemRepository;
import order.com.br.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class OrderServiceImpl implements OrderService {

    private final Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

    @Autowired
    OrderRepository repository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Page<Order> findAll(Pageable pageable) {
        logger.info("Finding all order.");
        return repository.findAll(pageable);
    }

    @Override
    public Order findById(UUID id) {
        logger.info("Finding a order.");
        return repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("No record found for this ID!")));
    }

    @Override
    public Order create(Order order) {
        logger.info("Creating a order.");
        var customer = new Customer();
        customer.setId(order.getCustomer().getId());
        customer.setName(order.getCustomer().getName());
        customer.setEmail(order.getCustomer().getEmail());
        customer.setPhone(order.getCustomer().getPhone());

        var entity = new Order();
        entity.setCustomer(customer);
        entity.setOrderDate(order.getOrderDate());
        entity.setTotal(order.getTotal());

        repository.save(entity);

        var items = order.getItems()
            .stream()
            .map(item -> {
                var itemOrder = new Item();
                itemOrder.setOrder(entity);
                itemOrder.setPrice(item.getPrice());
                itemOrder.setQuantity(item.getQuantity());

                var product = new Product();
                product.setId(item.getProduct().getId());
                product.setName(item.getProduct().getName());
                product.setDescription(item.getProduct().getDescription());
                product.setPrice(item.getProduct().getPrice());

                itemOrder.setProduct(product);

                return  itemOrder;
            })
            .toList();

        itemRepository.saveAll(items);

        entity.setItems(items);

        return entity;
    }

    @Override
    public Order update(UUID id, Order order) {
        logger.info("Updating a order.");

        var customer = new Customer();
        customer.setId(order.getCustomer().getId());
        customer.setName(order.getCustomer().getName());
        customer.setEmail(order.getCustomer().getEmail());
        customer.setPhone(order.getCustomer().getPhone());

        var entity = repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(("No record found for this ID!")));

        entity.setId(id);
        entity.setOrderDate(order.getOrderDate());
        entity.setTotal(order.getTotal());
        entity.setCustomer(customer);

        repository.save(entity);

        var items = order.getItems()
            .stream()
            .map(item -> {
                var itemOrder = new Item();
                itemOrder.setOrder(entity);
                itemOrder.setPrice(item.getPrice());
                itemOrder.setQuantity(item.getQuantity());

                var product = new Product();
                product.setId(item.getProduct().getId());
                product.setName(item.getProduct().getName());
                product.setDescription(item.getProduct().getDescription());
                product.setPrice(item.getProduct().getPrice());

                itemOrder.setProduct(product);

                return  itemOrder;
            })
            .toList();

        itemRepository.saveAll(items);

        entity.setItems(items);

        return entity;
    }

    @Override
    public void delete(UUID id) {

        logger.info("Deleting a order.");

        var entity = repository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(("No record found for this ID!")));

        repository.delete(entity);
    }
}
