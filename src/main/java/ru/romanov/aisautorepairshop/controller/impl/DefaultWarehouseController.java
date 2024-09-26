package ru.romanov.aisautorepairshop.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanov.aisautorepairshop.controller.WarehouseController;
import ru.romanov.aisautorepairshop.model.dto.ItemDto;
import ru.romanov.aisautorepairshop.model.dto.WarehouseDto;
import ru.romanov.aisautorepairshop.model.entity.Item;
import ru.romanov.aisautorepairshop.service.WarehouseService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/warehouse")
public class DefaultWarehouseController implements WarehouseController {
    private final WarehouseService warehouseService;

    @Override
    @PostMapping("/item")
    public ResponseEntity<Item> createItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.status(201).body(warehouseService.addItem(itemDto));
    }

    @Override
    @GetMapping("/item")
    public ResponseEntity<Item> getItemById(@RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(warehouseService.getItemById(itemDto.getUid()));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = warehouseService.getAllItems();
        return ResponseEntity.status(items.isEmpty() ? 204 : 200).body(items);
    }

    @Override
    @PatchMapping("/item")
    public ResponseEntity<Item> updateQuantityItem(@RequestBody WarehouseDto warehouseDto) {
        return ResponseEntity.ok(warehouseService.updateQuantityItem(warehouseDto.getUid(), warehouseDto.getQuantity(), warehouseDto.isAdding()));
    }

    @Override
    @DeleteMapping("/item")
    public ResponseEntity<Void> deleteItem(@RequestBody ItemDto itemDto) {
        warehouseService.deleteItemByUid(itemDto.getUid());
        return ResponseEntity.noContent().build();
    }
}
