package es.albarregas.models;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

/**
 *
 * Modelo que nos va a servir para transformar los Enums a texto para el BeansUtils
 * 
 * @author alberto
 */
public class EnumConverter implements Converter {

    @Override
    public Object convert(Class type, Object o) {
        String enumValName = (String) o;
        Enum[] enumConstants = (Enum[]) type.getEnumConstants();

        for (Enum enumConstant : enumConstants) {
            if (enumConstant.name().equals(enumValName)) {
                return (Object) enumConstant;
            }
        }

        throw new ConversionException(String.format("Failed to convert %s value to %s class", enumValName, type.toString()));
    }
    
}
