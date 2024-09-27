package ru.romanov.aisautorepairshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.aisautorepairshop.exception.ItemPartFoundException;
import ru.romanov.aisautorepairshop.model.dto.InventoryRequirementDto;
import ru.romanov.aisautorepairshop.model.dto.ItemDto;
import ru.romanov.aisautorepairshop.model.entity.InventoryRequirement;
import ru.romanov.aisautorepairshop.model.entity.Item;
import ru.romanov.aisautorepairshop.model.entity.Operation;
import ru.romanov.aisautorepairshop.model.entity.Warehouse;
import ru.romanov.aisautorepairshop.repository.InventoryRequirementRepository;
import ru.romanov.aisautorepairshop.repository.ItemRepository;
import ru.romanov.aisautorepairshop.repository.WarehouseRepository;
import ru.romanov.aisautorepairshop.service.WarehouseService;
import ru.romanov.aisautorepairshop.service.cache.WarehouseCacheService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DefaultWarehouseService implements WarehouseService {
    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;
    private final InventoryRequirementRepository requirementRepository;

    private final WarehouseCacheService warehouseCacheService;

    @Override
    @Transactional
    @CacheEvict(value = { "items", "warehouses" }, allEntries = true)
    public Item addItem(ItemDto itemDto) {
        if (itemRepository.existsByPartName(itemDto.getPartName()))
            throw new ItemPartFoundException("Part name already exists");
        Item newItem = itemRepository.save(
                Item.builder()
                        .partName(validateItemPartName(itemDto.getPartName()))
                        .build());
        warehouseRepository.save(
                Warehouse.builder()
                        .item(newItem)
                        .quantity(itemDto.getQuantity())
                        .build());
        return newItem;
    }

    @Override
    public Item getItemByUid(UUID uid) {
        return warehouseCacheService.getItemByUid(uid);
    }

    @Override
    public Warehouse getWarehouseByItemUid(UUID uid) {
        return warehouseCacheService.getWarehouseByItemUid(uid);
    }

    @Override
    public InventoryRequirement getInventoryRequirementByUid(UUID uid) {
        return warehouseCacheService.getInventoryRequirementByUid(uid);
    }

    @Override
    public List<Item> getAllItems() {
        return warehouseCacheService.getAllItems();
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseCacheService.getAllWarehouses();
    }

    @Override
    public int getItemQuantityByUid(UUID itemUid) {
        return warehouseCacheService.getItemQuantityByUid(itemUid);
    }

    @Override
    @CacheEvict(value = { "warehouses", "item_quantities" }, allEntries = true)
    public Item updateQuantityItem(UUID itemUid, int quantity, boolean isAdding) {
        if (quantity != 0) {
            Warehouse warehouse = getWarehouseByItemUid(itemUid);
            int validQuantity = validateItemQuantity(quantity);
            int currentQuantity = warehouse.getQuantity();
            currentQuantity += isAdding ? validQuantity : -validQuantity;
            warehouse.setQuantity(validateItemQuantity(currentQuantity));
            return warehouseRepository.save(warehouse).getItem();
        } else throw new IllegalArgumentException("Invalid quantity = " + quantity);
    }


    @Override
    @Transactional
    @CacheEvict(value = { "items", "warehouses", "inventory_requirements", "item_quantities" }, key = "#uid", allEntries = true)
    public void deleteItemByUid(UUID uid) {
        requirementRepository.deleteInventoryRequirementByItemUid(uid);
        warehouseRepository.deleteWarehouseByItemUid(uid);
        itemRepository.deleteById(uid);
    }

    @CacheEvict(value = { "item_quantities" }, allEntries = true)
    @Override
    @Transactional
    public void takeRequirementItems(List<InventoryRequirementDto> requirementDtoList, Operation operation) {
        requirementDtoList.forEach(inventoryRequirementDto -> {
            int oldQuantity = getItemQuantityByUid(inventoryRequirementDto.getItem_uid());
            int currentQuantity = validateItemQuantity(oldQuantity - inventoryRequirementDto.getQuantity());
            updateQuantityItem(inventoryRequirementDto.getItem_uid(), inventoryRequirementDto.getQuantity(), false);
            requirementRepository.save(
                    InventoryRequirement.builder()
                            .operation(operation)
                            .item(getItemByUid(inventoryRequirementDto.getItem_uid()))
                            .quantity(currentQuantity)
                            .build());
        });
    }

    private String validateItemPartName(String partName) {
        if (partName == null || partName.trim().isEmpty()) throw new IllegalArgumentException();
        else return partName.trim();
    }

    private int validateItemQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity must be greater than 0");
        return quantity;
    }
}
