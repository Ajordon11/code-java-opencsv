package com.idc.example.table;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class holds actual table representation of the data from csv file.
 */
public class Table {
    private List<TableRow> rows = new ArrayList<>();

    public Table() {
    }
    
    public List<TableRow> getRows() {
        return rows;
    }

    public void setRows(List<TableRow> rows) {
        this.rows = rows;
    }

    public void printTable() {
        System.out.println("Table:");
        rows.forEach(row -> System.out.println(row));
    }

    public Table sortedByVendor(boolean asc) {
        ArrayList<TableRow> rowsToSort = new ArrayList<>(this.rows);
        Table newTable = new Table();
        rowsToSort.sort(Comparator.comparing(TableRow::vendor, asc ? Comparator.naturalOrder() : Comparator.reverseOrder()));
        newTable.setRows(rowsToSort);
        return newTable;
    }

    public Table sortedByUnits(boolean asc) {
        ArrayList<TableRow> rowsToSort = new ArrayList<>(this.rows);
        Table newTable = new Table();
        rowsToSort.sort(Comparator.comparing(TableRow::units, asc ? Comparator.naturalOrder() : Comparator.reverseOrder()));
        newTable.setRows(rowsToSort);
        return newTable;
    }
}
