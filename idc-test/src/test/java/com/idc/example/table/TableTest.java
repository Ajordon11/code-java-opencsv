package com.idc.example.table;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TableTest {

    @Test
    void testSortByUnits() {
        Table table = getTestData();
        
        Table sortedAsc = table.sortedByUnits(true);
        Table sortedDesc = table.sortedByUnits(false);

        Assertions.assertEquals(6, sortedAsc.getRows().size());
        Assertions.assertEquals(6, sortedDesc.getRows().size());

        Assertions.assertEquals(10L, sortedAsc.getRows().getFirst().units());
        Assertions.assertEquals(50L, sortedAsc.getRows().getLast().units());
        
        Assertions.assertEquals(50L, sortedDesc.getRows().getFirst().units());
        Assertions.assertEquals(10L, sortedDesc.getRows().getLast().units());

    }

    @Test
    void testSortByVendor() {
        Table table = getTestData();

        Table sortedAsc = table.sortedByVendor(true);
        Table sortedDesc = table.sortedByVendor(false);

        Assertions.assertEquals(6, sortedAsc.getRows().size());
        Assertions.assertEquals(6, sortedDesc.getRows().size());

        Assertions.assertEquals("Vendor1", sortedAsc.getRows().getFirst().vendor());
        Assertions.assertEquals("Vendor8", sortedAsc.getRows().getLast().vendor());
        
        Assertions.assertEquals("Vendor8", sortedDesc.getRows().getFirst().vendor());
        Assertions.assertEquals("Vendor1", sortedDesc.getRows().getLast().vendor());
    }

    private Table getTestData() {
        Table table = new Table();
        table.setRows(List.of(
                new TableRow("Vendor3", 10L, 0.05),
                new TableRow("Vendor2", 20L, 0.1),
                new TableRow("Vendor5", 30L, 0.15),
                new TableRow("Vendor4", 40L, 0.2),
                new TableRow("Vendor8", 50L, 0.25),
                new TableRow("Vendor1", 50L, 0.25)
        ));
        return table;
    }

}
