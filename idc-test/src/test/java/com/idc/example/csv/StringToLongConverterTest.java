package com.idc.example.csv;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class StringToLongConverterTest {

    private static StringToLongConverter converter;

    @BeforeAll
    public static void setup() {
        converter = new StringToLongConverter();
    }

    @SuppressWarnings("unused")
    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("123", 123L),
                Arguments.of("123.450", 123450L),
                Arguments.of("123.000", 123000L),
                Arguments.of("12345678900", 12345678900L),
                Arguments.of("", 0L),
                Arguments.of("  ", 0L),
                Arguments.of(null, 0L)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    public void testStringToIntConversion(String value, long expected) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        long result = (long) converter.convert(value);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testInvalidIntConversion() {
        Exception ex = Assertions.assertThrows(NumberFormatException.class, () -> {
            converter.convert("abc");
        });

        Assertions.assertEquals("For input string: \"abc\"", ex.getMessage());
    }

}
