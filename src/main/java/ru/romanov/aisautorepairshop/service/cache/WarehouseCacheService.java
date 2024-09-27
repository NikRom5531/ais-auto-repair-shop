package ru.romanov.aisautorepairshop.service.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.romanov.aisautorepairshop.exception.EntityNotFoundException;
import ru.romanov.aisautorepairshop.model.entity.InventoryRequirement;
import ru.romanov.aisautorepairshop.model.entity.Item;
import ru.romanov.aisautorepairshop.model.entity.Warehouse;
import ru.romanov.aisautorepairshop.repository.InventoryRequirementRepository;
import ru.romanov.aisautorepairshop.repository.ItemRepository;
import ru.romanov.aisautorepairshop.repository.WarehouseRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarehouseCacheService {
    private final ItemRepository partItemRepository;
    private final WarehouseRepository warehouseRepository;
    private final InventoryRequirementRepository requirementRepository;

    @Cacheable(value = "items", key = "#uid")
    public Item getItemByUid(UUID uid) {
        return partItemRepository.findById(uid)
                .orElseThrow(() -> new EntityNotFoundException("Item by UID = " + uid + " not found"));
    }

    @Cacheable(value = "warehouses", key = "#uid")
    public Warehouse getWarehouseByItemUid(UUID uid) {
        return warehouseRepository.findByItemUid(uid)
                .orElseThrow(() -> new EntityNotFoundException("Warehouse by UID = " + uid + " not found"));
    }

    @Cacheable(value = "inventory_requirements", key = "#uid")
    public InventoryRequirement getInventoryRequirementByUid(UUID uid) {
        return requirementRepository.findById(uid)
                .orElseThrow(() -> new EntityNotFoundException("Inventory Requirement by UID = " + uid + " not found"));
    }

    @Cacheable("items")
    public List<Item> getAllItems() {
        return partItemRepository.findAll();
    }

    @Cacheable(value = "item_quantities", key = "#uid")
    public int getItemQuantityByUid(UUID uid) {
        return warehouseRepository.findWarehouseQuantityByItemUid(uid)
                .orElseThrow(() -> new EntityNotFoundException("Quantity Warehouse by UID = " + uid + " not found"));
    }
}
