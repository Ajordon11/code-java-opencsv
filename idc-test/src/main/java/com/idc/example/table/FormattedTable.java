package com.idc.example.table;

import java.util.List;
import java.util.Locale;

/**
 * This class holds formatted table representation (view) of the data from csv file.
 * What this means: 
 *  - only top {@link TableExportService#NUM_DISPLAYED_VENDORS} vendors by units and share are shown
 *  - rest is shown in "Other" row
 *  - table is sorted by units by default (desc)
 *  - last row is "Total"
 * @see com.idc.example.table.TableExportService#getFormattedTable(Table)
 */
public class FormattedTable extends Table {
    @Override
    public void printTable() {
        getRows().forEach(row -> {
            if (row.vendor().equals("Hewlett-Packard")) {
                System.out.println("HP\t|\t" + withThousandsDelimiter(row.units()) + "\t|\t" + toPercent(row.share()));
                return;
            }
            System.out.println(row.vendor() + "\t|\t" + withThousandsDelimiter(row.units()) + "\t|\t" + toPercent(row.share()));
        });
    }

    public FormattedTable(List<TableRow> rows) {
        super(rows);
    }

    private String toPercent(double share) {
        return String.format("%.2f", share * 100) + "%";
    }

    private String withThousandsDelimiter(long units) {
        return String.format(Locale.US, "%,d", units);
    }
    
}
