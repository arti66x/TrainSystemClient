package com.example.rzdwebapp.data.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BrigadeType {
    REPAIR_BRIGADE("бригада ремонтников"),
    LOCOMOTIVE_BRIGADE("локомотивная бригада");
    private final String name;

    BrigadeType(String name) {
        this.name = name;
    }
    @JsonValue
    public String getName(){
        return name;
    }
}
