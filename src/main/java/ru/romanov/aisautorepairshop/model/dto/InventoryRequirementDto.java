package ru.romanov.aisautorepairshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import ru.romanov.aisautorepairshop.model.entity.Item;
//import ru.romanov.aisautorepairshop.model.entity.Operation;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryRequirementDto {
    private UUID uid;
//    private Operation operation;
//    private Item item;
    private int quantity;

    private UUID item_uid;
    private UUID operation_uid;
}
