package com.app.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

// Converts a String to a Location in query parameters
// Also to be used as a general purpose conversion for deserialiazation
// Registered to the list of the converters in ControllerConfig class
// - as a result, this converter is used whenever Location appears
// as a query parameter

// annotated component so that it can be autowired into the LocationDeserializer
// component vs. bean: https://www.baeldung.com/spring-component-annotation

@Component
@Deprecated
// was used for the above, but since we implemented a deserializer, that is used instead globally
public class StringToLocationConverter implements Converter<String, Location> {
    @Override
    public Location convert(String source) {
        return Location.valueOf(source.toUpperCase());
    }
}
