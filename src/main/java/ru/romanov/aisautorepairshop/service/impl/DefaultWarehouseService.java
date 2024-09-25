package ru.romanov.aisautorepairshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.romanov.aisautorepairshop.exception.ItemNotFoundException;
import ru.romanov.aisautorepairshop.model.dto.ItemDto;
import ru.romanov.aisautorepairshop.model.dto.WarehouseDto;
import ru.romanov.aisautorepairshop.model.entity.Item;
import ru.romanov.aisautorepairshop.model.entity.Warehouse;
import ru.romanov.aisautorepairshop.repository.ItemRepository;
import ru.romanov.aisautorepairshop.repository.WarehouseRepository;
import ru.romanov.aisautorepairshop.service.WarehouseService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DefaultWarehouseService {
    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;

//    @Override
    public Item addItem(ItemDto itemDto) {
        Item newItem = itemRepository.save(
                Item.builder()
                        .partName(itemDto.getPartName())
                        .build());
        warehouseRepository.save(
                Warehouse.builder()
                        .item(newItem)
                        .quantity(itemDto.getQuantity())
                        .build());
        return newItem;
    }

//    @Override
    public Item getItemById(UUID uid) {
        return itemRepository.findById(uid)
                .orElseThrow(() -> new ItemNotFoundException("Inventory item not found"));
    }

//    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }


//    @Override
    public Item updateQuantityItem(WarehouseDto warehouseDto) {
        Warehouse warehouse = warehouseRepository.findByItem(getItemById(warehouseDto.getItem_uid()));
        int quantity = warehouseDto.getQuantity();
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0");
        int currentQuantity = warehouse.getQuantity();
        currentQuantity += warehouseDto.isAdding() ? quantity : -quantity;
        if (!warehouseDto.isAdding() && currentQuantity < 0)
            throw new IllegalArgumentException("Insufficient quantity in stock");
        warehouse.setQuantity(currentQuantity);
        return warehouseRepository.save(warehouse).getItem();
    }

//    @Override
    public void deleteItem(UUID uid) {
        warehouseRepository.delete(warehouseRepository.findByItem(getItemById(uid)));
        itemRepository.deleteById(uid);
    }

    private String validateItemPartName(String partName) {
        if (partName == null || partName.trim().isEmpty()) throw new IllegalArgumentException();
        else return partName.trim();
    }

    private int validateItemQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException();
        return quantity;
    }
}
