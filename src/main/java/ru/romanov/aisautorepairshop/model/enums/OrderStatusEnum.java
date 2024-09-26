package ru.romanov.aisautorepairshop.model.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    NEW("New"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),;
    private final String string;

    OrderStatusEnum(String string) {
        this.string = string;
    }
}
