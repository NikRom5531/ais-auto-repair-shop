package ru.romanov.aisautorepairshop.model.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseDto {
    private UUID uid;

    @Min(1)
    private int quantity;

    private UUID item_uid;
    private boolean isAdding;
}
