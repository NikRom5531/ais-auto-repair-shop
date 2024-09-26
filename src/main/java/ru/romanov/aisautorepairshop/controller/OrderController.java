package ru.romanov.aisautorepairshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.romanov.aisautorepairshop.model.dto.OrderDto;
import ru.romanov.aisautorepairshop.model.entity.Order;

import java.util.List;


public interface OrderController {
    ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto);

    ResponseEntity<Order> getOrderById(@RequestBody OrderDto orderDto);

    ResponseEntity<List<Order>> getAllOrders();

    ResponseEntity<List<Order>> getOrdersByStatus(@RequestBody OrderDto orderDto);

    ResponseEntity<Order> changeOrderStatus(@RequestBody OrderDto orderDto);

    ResponseEntity<Void> deleteOrder(@RequestBody OrderDto orderDto);
}
