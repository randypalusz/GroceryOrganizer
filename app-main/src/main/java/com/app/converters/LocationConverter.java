package com.app.converters;

import com.app.enums.Location;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

// For PantryItem location <-> DB storage
// (DB <-> Entity)
// OLD_FIXME: autoApply not working - need to specify this converter in the PantryItem class.
// Fixed by adding the package containing this to the @EntityScan
@Converter(autoApply = true)
public class LocationConverter implements AttributeConverter<Location, String> {
    // Converts the location attribute to how it will appear in the DB
    @Override
    public String convertToDatabaseColumn(Location location) {
        if (location == null) {
            return null;
        }
        return location.getLocationCode();
    }

    // Converts the DB column to a Location enum
    @Override
    public Location convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return Stream.of(Location.values()).filter(c -> c.getLocationCode().equals(s)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
