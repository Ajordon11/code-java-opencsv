package com.idc.example.table;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public void exportToConsole(FormattedTable table) {
        table.printTable();
    }

    public void exportToHTML(FormattedTable table, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("<html><head>");
            writer.write("<title>Table</title>");
            addCss(writer);
            writer.write("</head><body><table>");
            writer.write("<thead><tr><th>Vendor</th><th>Units</th><th>Share</th></tr></thead>");
            writer.write("<tbody>");
            for (TableRow row : table.getRows()) {
                if (row.vendor().equals(TOTAL)) {
                    writer.write("<tr class=\"total\">");
                } else {
                    writer.write("<tr>");
                }
                String vendor = row.vendor();
                if (vendor.equals("Hewlett-Packard")) {
                    vendor = "HP";
                }
                writer.write("<td>" + vendor + "</td><td>" + withThousandsDelimiter(row.units()) + "</td><td>" + toPercent(row.share()) + "</td></tr>");
            }
            writer.write("</tbody></table></body></html>");
        } catch (IOException e) {
            System.err.println("Error exporting to HTML: " + e.getMessage());
        }
    }

    public void exportToCSV(FormattedTable table) {
        throw new UnsupportedOperationException("CSV export is not implemented yet");
    }

    public void exportToExcel(FormattedTable table) {
        throw new UnsupportedOperationException("Excel export is not implemented yet");
    }

    public static String toPercent(double share) {
        return String.format("%.2f", share * 100) + "%";
    }

    public static String withThousandsDelimiter(long units) {
        return String.format(Locale.US, "%,d", units);
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

    private void addCss(BufferedWriter writer) throws IOException {
        writer.write("<style>");
        writer.write("table, th, td {border: 1px solid black; text-align: center;}");
        writer.write("th, td {width: 150px; font-weight: normal;}");
        writer.write("table {border-collapse: collapse;}");
        writer.write("tr.total {background-color: lightyellow;}");
        writer.write("thead > tr {background-color: lightgray;}");
        writer.write("</style>");
    }
}
