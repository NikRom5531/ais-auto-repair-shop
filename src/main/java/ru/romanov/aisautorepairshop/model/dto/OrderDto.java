package ru.romanov.aisautorepairshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.romanov.aisautorepairshop.model.enums.OrderStatusEnum;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private UUID uid;
    private String customerName;
    private OrderStatusEnum status;
    private LocalDateTime created;
    private String description;
}
