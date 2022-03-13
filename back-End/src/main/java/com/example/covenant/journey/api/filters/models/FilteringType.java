package com.example.covenant.journey.api.filters.models;

public enum FilteringType {
    EQUAL("="),
    NOT_EQUAL("!="),

    CONTAIN(":"),

    GREATER_THEN(">"),
    GREATER_OR_EQUAL(">="),

    LESS_THEN("<"),
    LESS_OR_EQUAL("<="),

    IN("_="),

    EMPTY("empty") ;

    private final String code;

    FilteringType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static FilteringType fromString(String text) {
        for (FilteringType type: FilteringType.values()) {
            if (type.code.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new RuntimeException("Unknown filtering operation '" + text + "'");
    }
}