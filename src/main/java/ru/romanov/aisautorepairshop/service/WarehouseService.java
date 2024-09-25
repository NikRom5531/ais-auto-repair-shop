package ru.romanov.aisautorepairshop.service;

import ru.romanov.aisautorepairshop.model.dto.ItemDto;
import ru.romanov.aisautorepairshop.model.entity.Item;
import ru.romanov.aisautorepairshop.model.enums.ItemUpdateTypeEnum;

import java.util.List;
import java.util.UUID;

public interface WarehouseService {
    Item createItem(ItemDto payload);
    Item getItemById(UUID uid);
    List<Item> getAllItems();
    Item updateQuantityItem(UUID uid, int quantity, ItemUpdateTypeEnum type);
    void deleteItem(UUID uid);
}
