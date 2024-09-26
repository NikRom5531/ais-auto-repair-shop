package ru.romanov.aisautorepairshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.romanov.aisautorepairshop.exceptions.ItemNotFoundException;
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

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DefaultWarehouseService implements WarehouseService {
    private final ItemRepository partItemRepository;
    private final WarehouseRepository warehouseRepository;
    private final InventoryRequirementRepository requirementRepository;

    @Override
    public Item addItem(ItemDto itemDto) {
        Item newItem = partItemRepository.save(
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
    public Item getItemById(UUID uid) {
        return partItemRepository.findById(uid)
                .orElseThrow(() -> new ItemNotFoundException("Inventory item not found"));
    }

    @Override
    public List<Item> getAllItems() {
        return partItemRepository.findAll();
    }


    @Override
    public Item updateQuantityItem(UUID itemUid, int quantity, boolean isAdding) {
        if (quantity != 0) {
            Warehouse warehouse = warehouseRepository.findByItemUid(itemUid);
            int validQuantity = validateItemQuantity(quantity);
            int currentQuantity = warehouse.getQuantity();
            currentQuantity += isAdding ? validQuantity : -validQuantity;
            warehouse.setQuantity(validateItemQuantity(currentQuantity));
            return warehouseRepository.save(warehouse).getItem();
        } else throw new IllegalArgumentException("Invalid quantity");
    }

    @Override
    public void deleteItemByUid(UUID uid) {
//        requirementRepository.delete();
        warehouseRepository.deleteWarehouseByItemUid(uid);
        partItemRepository.deleteById(uid);
    }

    @Override
    public void takeRequirementItems(List<InventoryRequirementDto> requirementDtoList, Operation operation) {
        for (InventoryRequirementDto inventoryRequirementDto : requirementDtoList) {
            requirementRepository.save(
                    InventoryRequirement.builder()
                            .operation(operation)
                            .item(getItemById(inventoryRequirementDto.getItem_uid()))
                            .quantity(inventoryRequirementDto.getQuantity())
                            .build());
        }
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
