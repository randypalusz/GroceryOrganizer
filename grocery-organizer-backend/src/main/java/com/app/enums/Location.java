package com.app.enums;

import com.app.converters.LocationDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = LocationDeserializer.class)
public enum Location {
    FREEZER("F"),
    REFRIGERATOR("R"),
    PANTRY("P");

    private final String locationCode;

    private Location(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationCode() {
        return this.locationCode;
    }

    public static Location fromString(String source) {
        return Location.valueOf(source.toUpperCase());
    }
}
