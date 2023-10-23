package org.acme.converter;

import jakarta.ws.rs.ext.ParamConverter;
import org.acme.StatusEnum;
import org.acme.WrapperClass;

import java.lang.reflect.Type;

public class WrapperClassParamConverter implements ParamConverter<WrapperClass<?>> {

    private final Class<?> rawType;
    private final Type genericType;

    public WrapperClassParamConverter(
            final Class<?> rawType,
            final Type genericType
    ) {
        this.rawType = rawType;
        this.genericType = genericType;
    }

    @Override
    public WrapperClass<?> fromString(String value) {
        return new WrapperClass<>(Enum.valueOf(StatusEnum.class, value));
    }

    @Override
    public String toString(WrapperClass<?> wrapperClass) {
        return wrapperClass != null ? wrapperClass.getValue().toString() : null;
    }

}