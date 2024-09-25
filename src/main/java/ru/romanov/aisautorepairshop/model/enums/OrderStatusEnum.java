package ru.romanov.aisautorepairshop.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatusEnum {
    NEW("New"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),;
    private final String string;
}
