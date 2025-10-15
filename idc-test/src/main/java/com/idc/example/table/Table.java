package com.idc.example.table;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private List<TableRow> rows = new ArrayList<>();

    public Table() {
    }

    public Table(List<TableRow> rows) {
        rows.forEach(row -> System.out.println(row));
        this.rows = rows;
    }

    public List<TableRow> getRows() {
        return rows;
    }

    public void setRows(List<TableRow> rows) {
        this.rows = rows;
    }
}
