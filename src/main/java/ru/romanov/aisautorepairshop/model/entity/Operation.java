package ru.romanov.aisautorepairshop.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uid;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "started", nullable = false)
    private LocalDateTime started;

    @Column(name = "finished")
    private LocalDateTime finished;

    @ManyToOne
    @JoinColumn(name = "order_uid", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "employee_uid", nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "operation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<InventoryRequirement> inventoryRequirements;
}
