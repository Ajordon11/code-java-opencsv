package com.idc.example.table;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableService {

    public Table getFormattedTable(DataObject tableData) {
        return getFormattedTable(tableData, null);
    }

    public Table getFormattedTableForQuarter(DataObject tableData, String quarter) {
        return getFormattedTable(tableData, quarter);
    }

    /**
     * Build table in desired format for given data and quarter
     * @param tableData data loaded from CSV
     * @param quarter if null, table will be built for all quarters
     */
    public Table getFormattedTable(DataObject tableData, String quarter) {
        if (quarter != null) {
            tableData = tableData.getDataForSpecificQuarter(quarter);
        }
        Table table = convertDataToTable(tableData);
        return table;
    }

    private Table convertDataToTable(DataObject tableData) {
        Table table = new Table();
        Map<String, Long> vendorUnitsMap = new HashMap<>();
        
        tableData.getRows().forEach(row -> {
            String vendor = row.getVendor();
            vendorUnitsMap.merge(vendor, row.getUnits(), Long::sum);
        });
        
        List<TableRow> tableRows = vendorUnitsMap.entrySet().stream()
                .map(entry -> new TableRow(entry.getKey(), entry.getValue(), tableData.getShareForVendor(entry.getKey())))
                .toList();

        table.setRows(tableRows);
        return table;
    }

    public Table sortTableByVendor(Table table, boolean asc) {
        ArrayList<TableRow> rowsToSort = new ArrayList<>(table.getRows());
        Table newTable = new Table();
        rowsToSort.sort(Comparator.comparing(TableRow::vendor, asc ? Comparator.naturalOrder() : Comparator.reverseOrder()));
        newTable.setRows(rowsToSort);
        return newTable;
    }

    public Table sortTableByUnits(Table table,boolean asc) {
        ArrayList<TableRow> rowsToSort = new ArrayList<>(table.getRows());
        Table newTable = new Table();
        rowsToSort.sort(Comparator.comparing(TableRow::units, asc ? Comparator.naturalOrder() : Comparator.reverseOrder()));
        newTable.setRows(rowsToSort);
        return newTable;
    }
}
