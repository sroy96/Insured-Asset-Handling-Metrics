package com.acko.insuredassetcredibility.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public enum Events {

    FASTAG("fast tag"),
    ACCIDENT("accident"),
    REPAIR("repair"),
    MAINTENANCE("maintenance");

    private String eventName;

    Events(String eventName) {
        this.eventName = eventName;
    }
}
