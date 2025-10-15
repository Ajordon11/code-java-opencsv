package com.idc.example.table;

/**
 * This object holds actual table row representation of the data from csv file.
 */
public record TableRow(
    String vendor,
    long units, 
    double share) {}
