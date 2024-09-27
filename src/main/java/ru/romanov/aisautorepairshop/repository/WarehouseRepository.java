package ru.romanov.aisautorepairshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.romanov.aisautorepairshop.model.entity.Warehouse;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, UUID> {
    @Query("select wh.quantity from Warehouse wh where wh.item.uid = :itemUid")
    Optional<Integer> findWarehouseQuantityByItemUid(UUID itemUid);

    Optional<Warehouse> findByItemUid(UUID itemUid);

    void deleteWarehouseByItemUid(UUID itemUid);
}
