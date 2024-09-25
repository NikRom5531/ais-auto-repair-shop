package ru.romanov.aisautorepairshop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.romanov.aisautorepairshop.model.enums.OrderStatusEnum;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uid;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "description", nullable = false)
    private String description;
}