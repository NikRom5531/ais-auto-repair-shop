package ru.romanov.aisautorepairshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private UUID order_uid;
    private UUID employee_uid;

    private List<InventoryRequirementDto> requirementDtoList;
}
