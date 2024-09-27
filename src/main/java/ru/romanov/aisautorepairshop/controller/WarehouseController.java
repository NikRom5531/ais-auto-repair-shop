package ru.romanov.aisautorepairshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.romanov.aisautorepairshop.model.dto.InventoryRequirementDto;
import ru.romanov.aisautorepairshop.model.dto.ItemDto;
import ru.romanov.aisautorepairshop.model.dto.WarehouseDto;
import ru.romanov.aisautorepairshop.model.entity.InventoryRequirement;
import ru.romanov.aisautorepairshop.model.entity.Item;
import ru.romanov.aisautorepairshop.model.entity.Warehouse;

import java.util.List;

public interface WarehouseController {
    ResponseEntity<Item> createItem(@RequestBody ItemDto itemDto);

    ResponseEntity<Item> getItemByUid(@RequestBody ItemDto itemDto);

    ResponseEntity<List<Item>> getAllItems();

    ResponseEntity<List<Warehouse>> getAllWarehouses();

    ResponseEntity<Item> updateQuantityItem(@RequestBody WarehouseDto warehouseDto);

    ResponseEntity<Warehouse> getWarehouseByItemUid(@RequestBody WarehouseDto warehouseDto);

    ResponseEntity<InventoryRequirement> getInventoryRequirementByUid(@RequestBody InventoryRequirementDto inventoryRequirementDto);

    ResponseEntity<Integer> getItemQuantityByUid(@RequestBody WarehouseDto warehouseDto);

    ResponseEntity<Void> deleteItem(@RequestBody ItemDto itemDto);
}
