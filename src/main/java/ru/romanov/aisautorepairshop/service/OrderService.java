package ru.romanov.aisautorepairshop.service;

import ru.romanov.aisautorepairshop.model.entity.Order;
import ru.romanov.aisautorepairshop.model.enums.OrderStatusEnum;
import ru.romanov.aisautorepairshop.web.payload.OrderPayload;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    // Метод для создания нового заказа
    Order createOrder(OrderPayload payload);

    // Метод для получения заказа по ID
    Order getOrderById(UUID orderId);

    // Метод для получения всех заказов
    List<Order> getAllOrders();

    // Метод для получения заказов по его статусу
    List<Order> getOrdersByStatus(OrderStatusEnum status);

    // Метод для обновления существующего заказа
    Order changeOrderStatus(UUID uid, OrderStatusEnum status);

    // Метод для удаления заказа
    void deleteOrder(UUID orderId);
}
