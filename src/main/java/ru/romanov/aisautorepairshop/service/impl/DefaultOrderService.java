package ru.romanov.aisautorepairshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import ru.romanov.aisautorepairshop.exception.EntityNotFoundException;
import ru.romanov.aisautorepairshop.model.dto.OrderDto;
import ru.romanov.aisautorepairshop.model.entity.Order;
import ru.romanov.aisautorepairshop.model.enums.OrderStatusEnum;
import ru.romanov.aisautorepairshop.repository.OrderRepository;
import ru.romanov.aisautorepairshop.service.OrderService;
import ru.romanov.aisautorepairshop.service.cache.OrderCacheService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DefaultOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderCacheService orderCacheService;

    @CacheEvict(value = "orders", allEntries = true)
    @Override
    public Order createOrder(OrderDto orderDto) {
        return orderRepository.save(
                Order.builder()
                        .customerName(orderDto.getCustomerName())
                        .description(orderDto.getDescription())
                        .status(OrderStatusEnum.NEW)
                        .created(LocalDateTime.now())
                        .build());
    }

    @Override
    public Order getOrderById(UUID uid) {
        return orderCacheService.getOrderById(uid);
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatusEnum status) {
        return orderCacheService.getOrdersByStatus(status);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderCacheService.getAllOrders();
    }

    @CacheEvict(value = "orders", key = "#uid", allEntries = true)
    @Override
    public Order changeOrderStatus(UUID uid, OrderStatusEnum status) {
        Order existingOrder = getOrderById(uid);
        if (!existingOrder.getStatus().equals(status)) existingOrder.setStatus(status);
        return orderRepository.save(existingOrder);
    }

    @CacheEvict(value = "orders", key = "#uid", allEntries = true)
    @Override
    public void deleteOrder(UUID uid) {
        orderRepository.deleteById(uid);
    }
}
