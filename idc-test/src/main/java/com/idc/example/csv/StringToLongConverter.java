package com.idc.example.csv;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class StringToLongConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (value == null) {
            return 0L;
        }
        String trimmedValue = value.trim();
        if (trimmedValue.isEmpty()) {
            return 0L;
        }
        String longString = trimmedValue.replace(".", "");
        return (long) Long.parseLong(longString);
    }
}
