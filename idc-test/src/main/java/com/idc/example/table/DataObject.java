package com.idc.example.table;

import java.util.ArrayList;
import java.util.List;

import com.idc.example.csv.CsvDataObject;

public class DataObject extends CsvDataObject<DataRow> {

    private List<DataRow> rows = new ArrayList<>();

    public DataObject(List<DataRow> rows) {
        this.rows = rows;
    }

    public DataObject() {}

    public List<DataRow> getRows() {
        return rows;
    }

    public void setRows(List<DataRow> rows) {
        this.rows = rows;
    }

    public DataObject getDataForSpecificVendor(String vendor) {
        return getDataForVendorCountryAndQuarter(vendor, null, null);
    }

    public DataObject getDataForSpecificCounty(String country) {
        return getDataForVendorCountryAndQuarter(null, country, null);
    }

    public DataObject getDataForSpecificQuarter(String timescale) {
        return getDataForVendorCountryAndQuarter(null, null, timescale);
    }

    public DataObject getDataForVendorCountryAndQuarter(String vendor, String country, String timescale) {
        List<DataRow> selectedRows = getRows().stream()
                .filter(vendor == null ? r -> true : row -> row.getVendor().equals(vendor))
                .filter(country == null ? r -> true : row -> row.getCountry().equals(country))
                .filter(timescale == null ? r -> true : row -> row.getTimescale().equals(timescale))
                .toList();
        if (selectedRows.isEmpty()) {
            System.out.println("No data found for vendor: " + vendor + ", country: " + country + ", timescale: " + timescale);
        }
        DataObject dataSubset = new DataObject();
        dataSubset.setRows(selectedRows);
        return dataSubset;
    }

    public long getUnitsForVendor(String vendor) {
        return getRows().stream()
                .filter(row -> row.getVendor().equals(vendor))
                .mapToLong(DataRow::getUnits)
                .sum();
    }

    public double getShareForVendor(String vendor) {
        long totalUnits = getRows().stream()
                .mapToLong(DataRow::getUnits)
                .sum();
        long vendorUnits = getUnitsForVendor(vendor);
        if (totalUnits == 0) {
            System.err.println("Total units is 0, cannot calculate share for vendor: " + vendor);
            return 0;
        }
        return (double) vendorUnits / totalUnits;
    }
}
