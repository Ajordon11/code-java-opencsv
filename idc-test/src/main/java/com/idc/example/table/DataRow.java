package com.idc.example.table;

import com.idc.example.csv.CsvBean;
import com.idc.example.csv.StringToLongConverter;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

public class DataRow extends CsvBean {
    @CsvBindByName
    private String country;
    
    @CsvBindByName
    private String timescale;
    
    @CsvBindByName
    private String vendor;
    
    @CsvCustomBindByName(converter = StringToLongConverter.class)
    private long units;

    public DataRow(String country, String timescale, long units, String vendor) {
        this.country = country;
        this.timescale = timescale;
        this.units = units;
        this.vendor = vendor;
    }

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

    public long getUnits() {
        return units;
    }

    public void setUnits(long units) {
        this.units = units;
    }
    
}
