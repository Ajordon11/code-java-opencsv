package com.idc.example.table;

import com.idc.example.csv.CsvBean;
import com.opencsv.bean.CsvBindByName;

public class TableRow extends CsvBean {
    @CsvBindByName
    private String country;
    
    @CsvBindByName
    private String timescale;
    
    @CsvBindByName
    private String vendor;
    
    @CsvBindByName
    private double units;

    @Override
    public String toString() {
        return "" + country + ", " + timescale + ", " + vendor + ", " + units;
    } 

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimescale() {
        return timescale;
    }

    public void setTimescale(String timescale) {
        this.timescale = timescale;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }
}
