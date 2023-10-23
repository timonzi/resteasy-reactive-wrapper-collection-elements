package org.acme.converter;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;
import org.acme.WrapperClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class JsonParamConverterProvider implements ParamConverterProvider {

    @Override
    @SuppressWarnings("unchecked")
    public <T> ParamConverter<T> getConverter(
            final Class<T> rawType,
            final Type genericType,
            final Annotation[] annotations
    ) {

        if (WrapperClass.class.isAssignableFrom(rawType)) {
            return (ParamConverter<T>) new WrapperClassParamConverter(rawType, genericType);
        }

        // No specific converter for other types (constructors will be called directly)
        return null;
    }
}