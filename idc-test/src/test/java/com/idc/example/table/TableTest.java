package com.idc.example.table;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TableTest {
    @Test
    void testGetRowNumberForVendor() {
        Table table = getTestData();

        Assertions.assertEquals(6, table.getRowNumberForVendor("Vendor1"));
        Assertions.assertEquals(2, table.getRowNumberForVendor("Vendor2"));
        Assertions.assertEquals(1, table.getRowNumberForVendor("Vendor3"));
        Assertions.assertEquals(4, table.getRowNumberForVendor("Vendor4"));
        Assertions.assertEquals(3, table.getRowNumberForVendor("Vendor5"));
        Assertions.assertEquals(5, table.getRowNumberForVendor("Vendor8"));
    }

    @Test
    void testGetUnitsForVendorInView() {
        Table table = getTestData();

        Assertions.assertEquals(50L, table.getUnitsForVendorInView("Vendor1"));
        Assertions.assertEquals(20L, table.getUnitsForVendorInView("Vendor2"));
        Assertions.assertEquals(10L, table.getUnitsForVendorInView("Vendor3"));
        Assertions.assertEquals(40L, table.getUnitsForVendorInView("Vendor4"));
        Assertions.assertEquals(30L, table.getUnitsForVendorInView("Vendor5"));
        Assertions.assertEquals(50L, table.getUnitsForVendorInView("Vendor8"));
    }

    @Test
    void testGetShareForVendorInView() {
        Table table = getTestData();

        Assertions.assertEquals(0.25, table.getShareForVendorInView("Vendor1"));
        Assertions.assertEquals(0.1, table.getShareForVendorInView("Vendor2"));
        Assertions.assertEquals(0.05, table.getShareForVendorInView("Vendor3"));
        Assertions.assertEquals(0.2, table.getShareForVendorInView("Vendor4"));
        Assertions.assertEquals(0.15, table.getShareForVendorInView("Vendor5"));
        Assertions.assertEquals(0.25, table.getShareForVendorInView("Vendor8"));
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
