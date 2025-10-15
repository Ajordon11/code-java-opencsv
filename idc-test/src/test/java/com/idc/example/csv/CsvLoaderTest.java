package com.idc.example.csv;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CsvLoaderTest {

    private CsvLoader csvLoader;

    @BeforeEach
    public void setup() {
        csvLoader = new CsvLoader();
    }

    @Test
    public void testBuildBeansFromCsv_ValidCsv_FileExists() throws FileNotFoundException {
        String fileName = "src/main/resources/IDC-data.csv";

        CsvDataObject data = csvLoader.loadDataFromCsv(fileName, CsvBean.class, CsvDataObject.class);

        List<CsvBean> rows = data.getCsvRows();
        Assertions.assertNotNull(rows);
        Assertions.assertFalse(rows.isEmpty());
        Assertions.assertEquals(28, rows.size());
    }

    @Test
    public void testBuildBeansFromCsv_InvalidCsv_FileDoesNotExist() {
        String fileName = "nonexistent.csv";

        Exception ex = Assertions.assertThrows(FileNotFoundException.class, () -> {
            csvLoader.loadDataFromCsv(fileName, CsvBean.class, CsvDataObject.class);
        });

        Assertions.assertEquals(fileName + " (The system cannot find the file specified)", ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "src/test/resources/empty-header.csv", "src/test/resources/empty-no-header.csv" })
    public void testBuildBeansFromCsv_EmptyCsv_FileExists(String fileName) {
        Exception ex = Assertions.assertThrows(FileNotFoundException.class, () -> {
            csvLoader.loadDataFromCsv(fileName, CsvBean.class, CsvDataObject.class);
        });

        Assertions.assertEquals("Input file is empty: " + fileName, ex.getMessage());
    }
}
