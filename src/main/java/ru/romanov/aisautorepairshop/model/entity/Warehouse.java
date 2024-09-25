package ru.romanov.aisautorepairshop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Warehouse {
    private UUID uid;

    @ManyToOne
    @JoinColumn(name = "item_uid", nullable = false)
    private Item item;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
