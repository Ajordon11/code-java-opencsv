package com.idc.example.csv;

import java.util.List;

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
