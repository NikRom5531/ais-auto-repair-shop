package ru.romanov.aisautorepairshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.romanov.aisautorepairshop.model.entity.Order;
import ru.romanov.aisautorepairshop.web.request.OrderRequest;
import ru.romanov.aisautorepairshop.web.request.OrderStatusRequest;
import ru.romanov.aisautorepairshop.web.request.UpdateOrderStatusRequest;
import ru.romanov.aisautorepairshop.web.request.UidRequest;

import java.util.List;


public interface OrderController {
    ResponseEntity<Order> createOrder(@RequestBody OrderRequest request);
    ResponseEntity<Order> getOrderById(@RequestBody UidRequest request);
    List<Order> getAllOrders();
    List<Order> getOrderById(@RequestBody OrderStatusRequest request);
    ResponseEntity<Order> changeOrderStatus(@RequestBody UpdateOrderStatusRequest request);
    ResponseEntity<Void> deleteOrder(@RequestBody UidRequest request);
}
