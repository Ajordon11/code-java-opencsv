package com.idc.example.csv;

import java.util.List;

/**
 * Default class for loading data from csv file.
 * Classes that extend this class can hold List of objects extended from CsvBean
 */
public class CsvDataObject<T extends CsvBean> {
    private List<T> rows;

    public CsvDataObject(List<T> rows) {
        this.rows = rows;
    }

    public CsvDataObject() {}

    public List<T> getCsvRows() {
        return rows;
    }
}
