package com.idc.example.table;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class TableExportServiceTest {

    private TableExportService tableExportService;

    @BeforeEach
    public void setUp() {
        tableExportService = new TableExportService(new TableService());
    }

    @Test
    public void testGetFormattedTable() {
        Table table = getTestData();
        FormattedTable formattedTable = tableExportService.getFormattedTable(table);
        
        Assertions.assertEquals(5, formattedTable.getRows().size());
        Assertions.assertEquals("Vendor1", formattedTable.getRows().getFirst().vendor());

        Assertions.assertEquals("Others", formattedTable.getRows().get(3).vendor());
        Assertions.assertEquals(30L, formattedTable.getRows().get(3).units());
        Assertions.assertEquals(0.2, formattedTable.getRows().get(3).share());

        Assertions.assertEquals("Total", formattedTable.getRows().getLast().vendor());
        Assertions.assertEquals(150L, formattedTable.getRows().getLast().units());
        Assertions.assertEquals(1.0, formattedTable.getRows().getLast().share());
    }

    private Table getTestData() {
        Table table = new Table();
        table.setRows(List.of(
                new TableRow("Vendor3", 10L, 0.05),
                new TableRow("Vendor2", 20L, 0.1),
                new TableRow("Vendor5", 30L, 0.15),
                new TableRow("Vendor4", 40L, 0.2),
                new TableRow("Vendor1", 50L, 0.25)
        ));
        return table;
    }

}