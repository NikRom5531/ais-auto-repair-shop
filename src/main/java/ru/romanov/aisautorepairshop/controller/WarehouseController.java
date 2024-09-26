package ru.romanov.aisautorepairshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.romanov.aisautorepairshop.model.dto.ItemDto;
import ru.romanov.aisautorepairshop.model.dto.WarehouseDto;
import ru.romanov.aisautorepairshop.model.entity.Item;

import java.util.List;

public interface WarehouseController {
    ResponseEntity<Item> createItem(@RequestBody ItemDto itemDto);

    ResponseEntity<Item> getItemById(@RequestBody ItemDto itemDto);

    ResponseEntity<List<Item>> getAllItems();

    ResponseEntity<Item> updateQuantityItem(@RequestBody WarehouseDto warehouseDto);

    ResponseEntity<Void> deleteItem(@RequestBody ItemDto itemDto);
}
