package ru.romanov.aisautorepairshop.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "inventory_requirement")
public class InventoryRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uid;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "operation_uid", nullable = false)
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "item_uid", nullable = false)
    private Item item;

    @Column(nullable = false)
    private int quantity;
}
