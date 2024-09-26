package ru.romanov.aisautorepairshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.romanov.aisautorepairshop.model.entity.Item;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WarehouseDto {
    private UUID uid;
    private Item item;
    private int quantity;

    private UUID item_uid;
    private boolean isAdding;
}
