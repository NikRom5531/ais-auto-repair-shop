package ru.romanov.aisautorepairshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.romanov.aisautorepairshop.exception.OrderNotFoundException;
import ru.romanov.aisautorepairshop.model.entity.Order;
import ru.romanov.aisautorepairshop.model.enums.OrderStatusEnum;
import ru.romanov.aisautorepairshop.repository.OrderRepository;
import ru.romanov.aisautorepairshop.service.OrderService;
import ru.romanov.aisautorepairshop.web.payload.OrderPayload;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(OrderPayload payload) {
        return orderRepository.save(
                Order.builder()
                        .customerName(payload.getCustomerName())
                        .description(payload.getDescription())
                        .status(OrderStatusEnum.NEW)
                        .created(LocalDateTime.now())
                        .build());
    }

    @Override
    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new OrderNotFoundException("Order not found with id: " + orderId));
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatusEnum status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order changeOrderStatus(UUID uid, OrderStatusEnum status) {
        Order existingOrder = getOrderById(uid);
        if(!existingOrder.getStatus().equals(status)) existingOrder.setStatus(status);
        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(UUID orderId) {
        orderRepository.deleteById(orderId);
    }
}
