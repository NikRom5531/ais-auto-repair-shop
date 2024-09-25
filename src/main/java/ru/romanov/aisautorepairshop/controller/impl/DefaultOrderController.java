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
import ru.romanov.aisautorepairshop.model.entity.Order;
import ru.romanov.aisautorepairshop.service.OrderService;
import ru.romanov.aisautorepairshop.web.mapper.OrderMapper;
import ru.romanov.aisautorepairshop.web.mapper.UidMapper;
import ru.romanov.aisautorepairshop.web.payload.UpdateOrderStatusPayload;
import ru.romanov.aisautorepairshop.web.request.OrderRequest;
import ru.romanov.aisautorepairshop.web.request.OrderStatusRequest;
import ru.romanov.aisautorepairshop.web.request.UpdateOrderStatusRequest;
import ru.romanov.aisautorepairshop.web.request.UidRequest;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class DefaultOrderController implements OrderController {
    private OrderService orderService;

    @Override
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.status(201).body(orderService.createOrder(OrderMapper.INSTANCE.toPayload(request)));
    }

    @Override
    @GetMapping("/order")
    public ResponseEntity<Order> getOrderById(@RequestBody UidRequest request) {
        return ResponseEntity.ok(orderService.getOrderById(UidMapper.INSTANCE.toPayload(request).getUid()));
    }

    @Override
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Override
    @GetMapping("/status")
    public List<Order> getOrderById(@RequestBody OrderStatusRequest request) {
        return orderService.getOrdersByStatus(OrderMapper.INSTANCE.toPayload(request).getStatus());
    }

    @Override
    @PutMapping("/status")
    public ResponseEntity<Order> changeOrderStatus(@RequestBody UpdateOrderStatusRequest request) {
        UpdateOrderStatusPayload payload = OrderMapper.INSTANCE.toPayload(request);
        return ResponseEntity.status(200).body(orderService.changeOrderStatus(payload.getUid(), payload.getStatus()));
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deleteOrder(@RequestBody UidRequest request) {
        orderService.deleteOrder(UidMapper.INSTANCE.toPayload(request).getUid());
        return ResponseEntity.noContent().build();
    }
}
