package com.idc.example.table;

/**
 * This object holds actual table row representation of the data from csv file.
 * (only columns needed for export)
 */
public record TableRow(
    String vendor,
    long units, 
    double share) {}
