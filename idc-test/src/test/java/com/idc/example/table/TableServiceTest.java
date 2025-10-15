package com.idc.example.table;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TableServiceTest {

    private TableService tableService;

    @BeforeEach
    public void setUp() {
        tableService = new TableService();
    }

    @Test
    void testGetFormattedTable() {
        DataObject tableData = getTestData();

        Table result = tableService.getParsedTable(tableData);
        result.printTable();

        Assertions.assertEquals(3, result.getRows().size());
        TableRow vendor1Row = result.getRows().stream().filter(r -> r.vendor().equals("Vendor1")).findFirst().orElse(null);
        Assertions.assertNotNull(vendor1Row);
        Assertions.assertEquals(35, vendor1Row.units());
        Assertions.assertEquals(0.35, vendor1Row.share());

        TableRow vendor2Row = result.getRows().stream().filter(r -> r.vendor().equals("Vendor2")).findFirst().orElse(null);
        Assertions.assertNotNull(vendor2Row);
        Assertions.assertEquals(60L, vendor2Row.units());
        Assertions.assertEquals(0.6, vendor2Row.share());

        TableRow vendor3Row = result.getRows().stream().filter(r -> r.vendor().equals("Vendor3")).findFirst().orElse(null);
        Assertions.assertNotNull(vendor3Row);
        Assertions.assertEquals(5L, vendor3Row.units());
        Assertions.assertEquals(0.05, vendor3Row.share());

    }

    @Test
    void testGetFormattedTableForQuarter() {
        DataObject tableData = getTestData();

        Table result = tableService.getParsedTableForQuarter(tableData, "2010 Q2");

        Assertions.assertEquals(2, result.getRows().size());
        TableRow vendor1Row = result.getRows().stream().filter(r -> r.vendor().equals("Vendor1")).findFirst().orElse(null);
        Assertions.assertNotNull(vendor1Row);
        Assertions.assertEquals(10L, vendor1Row.units());
        Assertions.assertEquals(0.2, vendor1Row.share());

        TableRow vendor2Row = result.getRows().stream().filter(r -> r.vendor().equals("Vendor2")).findFirst().orElse(null);
        Assertions.assertNotNull(vendor2Row);
        Assertions.assertEquals(40L, vendor2Row.units());
        Assertions.assertEquals(0.8, vendor2Row.share());

        TableRow vendor3Row = result.getRows().stream().filter(r -> r.vendor().equals("Vendor3")).findFirst().orElse(null);
        Assertions.assertNull(vendor3Row);
    }

    @Test
    void testGetFormattedTableForQuarter_InvalidQuarter() {
        DataObject tableData = getTestData();

        Table result = tableService.getParsedTableForQuarter(tableData, "Q4");

        Assertions.assertEquals(0, result.getRows().size());
    }

    @Test
    void testSortByUnits() {
        DataObject tableData = getTestData();
        Table table = tableService.getParsedTable(tableData);

        Table sortedAsc = tableService.sortTableByUnits(table, true);
        Table sortedDesc = tableService.sortTableByUnits(table, false);

        Assertions.assertEquals(3, sortedAsc.getRows().size());
        Assertions.assertEquals(3, sortedDesc.getRows().size());

        Assertions.assertEquals(5L, sortedAsc.getRows().getFirst().units());
        Assertions.assertEquals(60L, sortedAsc.getRows().getLast().units());

        Assertions.assertEquals(60L, sortedDesc.getRows().getFirst().units());
        Assertions.assertEquals(5L, sortedDesc.getRows().getLast().units());

    }

    @Test
    void testSortByVendor() {
        DataObject tableData = getTestData();
        Table table = tableService.getParsedTable(tableData);

        Table sortedAsc = tableService.sortTableByVendor(table, true);
        Table sortedDesc = tableService.sortTableByVendor(table, false);

        Assertions.assertEquals(3, sortedAsc.getRows().size());
        Assertions.assertEquals(3, sortedDesc.getRows().size());

        Assertions.assertEquals("Vendor1", sortedAsc.getRows().getFirst().vendor());
        Assertions.assertEquals("Vendor3", sortedAsc.getRows().getLast().vendor());

        Assertions.assertEquals("Vendor3", sortedDesc.getRows().getFirst().vendor());
        Assertions.assertEquals("Vendor1", sortedDesc.getRows().getLast().vendor());
    }

    private DataObject getTestData() {
        DataObject tableData = new DataObject();
        tableData.setRows(List.of(
                new DataRow("Slovakia", "2010 Q1", 10, "Vendor1"),
                new DataRow("Czech Republic", "2010 Q1", 20, "Vendor2"),
                new DataRow("Poland", "2010 Q1", 5, "Vendor3"),
                new DataRow("Slovakia", "2010 Q2", 15, "Vendor2"),
                new DataRow("Czech Republic", "2010 Q2", 25, "Vendor2"),
                new DataRow("Poland", "2010 Q2", 10, "Vendor1"),
                new DataRow("Slovakia", "2010 Q3", 15, "Vendor1")
        ));
        return tableData;
    }
}
