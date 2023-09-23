package tech.picnic.assignment.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
    @JsonProperty("delivered")
    DELIVERED(1),
    @JsonProperty("cancelled")
    CANCELLED(0);

    private final int value;

    Status(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
