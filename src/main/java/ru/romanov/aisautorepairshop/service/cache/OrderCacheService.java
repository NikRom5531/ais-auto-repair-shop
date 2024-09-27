package ru.romanov.aisautorepairshop.service.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.romanov.aisautorepairshop.exception.EntityNotFoundException;
import ru.romanov.aisautorepairshop.model.entity.Order;
import ru.romanov.aisautorepairshop.model.enums.OrderStatusEnum;
import ru.romanov.aisautorepairshop.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderCacheService {
    private final OrderRepository orderRepository;

    @Cacheable(value = "orders", key = "#uid")
    public Order getOrderById(UUID uid) {
        return orderRepository.findById(uid)
                .orElseThrow(() -> new EntityNotFoundException("Order by UID = " + uid + " not found"));
    }

    @Cacheable(value = "orders", key = "#status")
    public List<Order> getOrdersByStatus(OrderStatusEnum status) {
        return orderRepository.findByStatus(status);
    }

    @Cacheable("orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
