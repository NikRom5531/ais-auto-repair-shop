package ru.romanov.aisautorepairshop.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanov.aisautorepairshop.controller.OrderController;
import ru.romanov.aisautorepairshop.model.dto.OrderDto;
import ru.romanov.aisautorepairshop.model.entity.Order;
import ru.romanov.aisautorepairshop.service.OrderService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class DefaultOrderController implements OrderController {
    private OrderService orderService;

    @Override
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto request) {
        return ResponseEntity.status(201).body(orderService.createOrder(request));
    }

    @Override
    @GetMapping("/order")
    public ResponseEntity<Order> getOrderById(@RequestBody OrderDto request) {
        return ResponseEntity.ok(orderService.getOrderById(request.getUid()));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.status(orders.isEmpty() ? 204 : 200).body(orders);
    }

    @Override
    @GetMapping("/status")
    public ResponseEntity<List<Order>> getOrdersByStatus(@RequestBody OrderDto request) {
        List<Order> orders = orderService.getOrdersByStatus(request.getStatus());
        return ResponseEntity.status(orders.isEmpty() ? 204 : 200).body(orders);
    }

    @Override
    @PutMapping("/status")
    public ResponseEntity<Order> changeOrderStatus(@RequestBody OrderDto request) {
        return ResponseEntity.status(200).body(orderService.changeOrderStatus(request.getUid(), request.getStatus()));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteOrder(@RequestBody OrderDto request) {
        orderService.deleteOrder(request.getUid());
        return ResponseEntity.noContent().build();
    }
}
