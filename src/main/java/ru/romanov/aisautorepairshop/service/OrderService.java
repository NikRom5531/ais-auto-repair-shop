package ru.romanov.aisautorepairshop.service;

import ru.romanov.aisautorepairshop.model.dto.OrderDto;
import ru.romanov.aisautorepairshop.model.entity.Order;
import ru.romanov.aisautorepairshop.model.enums.OrderStatusEnum;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order createOrder(OrderDto orderDto);

    Order getOrderById(UUID orderId);

    List<Order> getAllOrders();

    List<Order> getOrdersByStatus(OrderStatusEnum status);

    Order changeOrderStatus(UUID uid, OrderStatusEnum status);

    void deleteOrder(UUID orderId);
}
