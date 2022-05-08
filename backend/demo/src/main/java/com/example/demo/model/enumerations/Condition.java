package com.example.demo.model.enumerations;

public enum Condition {
    ASCCOST("По возрастанию цены"),
    DESCCOST("По убыванию цены");

    private final String value;
    Condition(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

