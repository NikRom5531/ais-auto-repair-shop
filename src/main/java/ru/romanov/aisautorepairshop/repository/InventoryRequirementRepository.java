package ru.romanov.aisautorepairshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.romanov.aisautorepairshop.model.entity.InventoryRequirement;

import java.util.UUID;

@Repository
public interface InventoryRequirementRepository extends JpaRepository<InventoryRequirement, UUID> {
}
