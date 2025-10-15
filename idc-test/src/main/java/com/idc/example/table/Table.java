package com.idc.example.table;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds actual table representation (view) of the data from csv
 * file.
 */
public class Table {

    private List<TableRow> rows = new ArrayList<>();

    public Table() {
    }

    public Table(List<TableRow> rows) {
        this.rows = rows;
    }

    public List<TableRow> getRows() {
        return rows;
    }

    public void setRows(List<TableRow> rows) {
        this.rows = rows;
    }

    public void printTable() {
        rows.forEach(row -> System.out.println(row));
    }

    /**
     * Returns row number for specific vendor (starting from 1).
     */
    public int getRowNumberForVendor(String vendor) {
        int index = rows.indexOf(rows.stream()
                .filter(r -> r.vendor().equals(vendor))
                .findFirst()
                .orElse(null));

        if (index == -1) {
            System.out.println("No data found for vendor: " + vendor);
        }
        return index + 1;
    }

    /**
     * Only return units for given vendor in this instance of table (reduced
     * from original csv data).
     */
    public long getUnitsForVendorInView(String vendor) {
        return rows.stream()
                .filter(r -> r.vendor().equals(vendor))
                .map(TableRow::units)
                .findFirst()
                .orElse(null);
    }

    /**
     * Only return share for given vendor in this instance of table (reduced
     * from original csv data).
     */
    public double getShareForVendorInView(String vendor) {
        return rows.stream()
                .filter(r -> r.vendor().equals(vendor))
                .map(TableRow::share)
                .findFirst()
                .orElse(null);
    }
}
