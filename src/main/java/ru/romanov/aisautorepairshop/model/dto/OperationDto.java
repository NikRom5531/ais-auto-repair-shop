package ru.romanov.aisautorepairshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import ru.romanov.aisautorepairshop.model.entity.Employee;
//import ru.romanov.aisautorepairshop.model.entity.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationDto {
    private UUID uid;
    private String description;
    private LocalDateTime started;
    private LocalDateTime finished;
//    private Order order;
//    private Employee employee;

    private UUID order_uid;
    private UUID employee_uid;

    private List<InventoryRequirementDto> requirementDtoList;
}
