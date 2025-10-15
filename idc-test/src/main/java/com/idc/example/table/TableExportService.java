package com.idc.example.table;

import java.util.ArrayList;
import java.util.List;

public class TableExportService {

    private final TableService tableService;
    private static final int NUM_DISPLAYED_VENDORS = 3;
    private static final String OTHERS = "Others";
    private static final String TOTAL = "Total";

    public TableExportService(TableService tableService) {
        this.tableService = tableService;
    }

    public FormattedTable getFormattedTable(Table table) {
        // ensure table is sorted
        Table sorted = tableService.sortTableByUnits(table, false);
        Long totalUnits = getTotalUnits(sorted);

        List<TableRow> rows = new ArrayList<>();
        sorted.getRows().subList(0, NUM_DISPLAYED_VENDORS).forEach(row -> {
            rows.add(row);
        });
        rows.add(createTableRowForOthers(sorted, totalUnits));
        rows.add(new TableRow(TOTAL, totalUnits, 1.0));
        return new FormattedTable(rows);
    }

    private Long getTotalUnits(Table table) {
        return table.getRows().stream()
                .map(TableRow::units)
                .reduce(0L, Long::sum);
    }

    private TableRow createTableRowForOthers(Table table, Long totalUnits) {
        if (totalUnits == 0) {
            // cannot divide by 0
            return new TableRow(OTHERS, 0L, 0.0);
        }

        Long othersUnits = table.getRows().subList(NUM_DISPLAYED_VENDORS, table.getRows().size())
                .stream()
                .map(TableRow::units)
                .reduce(0L, Long::sum);

        double othersShare = (double) othersUnits / totalUnits;

        return new TableRow(OTHERS, othersUnits, othersShare);
    }
}
