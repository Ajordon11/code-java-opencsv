package com.idc.example.csv;

import java.util.List;

public class CsvDataObject {
    private List<CsvBean> rows;

    public CsvDataObject(List<CsvBean> rows) {
        this.rows = rows;
    }

    public CsvDataObject() {}

    public List<CsvBean> getCsvRows() {
        return rows;
    }
}
