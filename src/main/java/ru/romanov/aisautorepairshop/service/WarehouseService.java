package ru.romanov.aisautorepairshop.service;

import ru.romanov.aisautorepairshop.model.dto.InventoryRequirementDto;
import ru.romanov.aisautorepairshop.model.dto.ItemDto;
import ru.romanov.aisautorepairshop.model.entity.InventoryRequirement;
import ru.romanov.aisautorepairshop.model.entity.Item;
import ru.romanov.aisautorepairshop.model.entity.Operation;
import ru.romanov.aisautorepairshop.model.entity.Warehouse;

import java.util.List;
import java.util.UUID;

public interface WarehouseService {
    Item addItem(ItemDto itemDto);

    Item getItemByUid(UUID uid);

    Warehouse getWarehouseByItemUid(UUID uid);

    InventoryRequirement getInventoryRequirementByUid(UUID uid);

    List<Item> getAllItems();

    List<Warehouse> getAllWarehouses();

    int getItemQuantityByUid(UUID uid);

    Item updateQuantityItem(UUID itemUid, int quantity, boolean isAdding);

    void deleteItemByUid(UUID uid);

    void takeRequirementItems(List<InventoryRequirementDto> requirementDto, Operation operation);
}
