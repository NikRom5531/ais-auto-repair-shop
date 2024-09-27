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
public class ItemDto {
    private UUID uid;
    private String partName;

    @Min(1)
    private int quantity;
}
