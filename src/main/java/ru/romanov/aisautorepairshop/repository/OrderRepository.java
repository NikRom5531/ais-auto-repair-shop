package ru.romanov.aisautorepairshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.romanov.aisautorepairshop.model.entity.Order;
import ru.romanov.aisautorepairshop.model.enums.OrderStatusEnum;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByStatus(OrderStatusEnum status);
}
