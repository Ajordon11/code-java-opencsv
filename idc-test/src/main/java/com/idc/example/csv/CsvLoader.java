package com.idc.example.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

public class CsvLoader {

    public boolean fileExists(String fileName) {
        File f = new File(fileName);
        return f.exists() && !f.isDirectory();
    }

    /**
     * Load data from csv file and convert it to list of objects
     * @param fileName
     * @param rowObjectClass extends CsvBean
     * @param dataObjectClass extends CsvDataObject
     * @return
     */
    public CsvDataObject loadDataFromCsv(String fileName, Class rowObjectClass) throws FileNotFoundException, InstantiationException, IllegalAccessException {
        List<CsvBean> rows = buildBeansFromCsv(fileName, rowObjectClass);
        return new CsvDataObject(rows);
    }
    private <T extends CsvBean> List<T> buildBeansFromCsv(String fileName, Class clazz) throws FileNotFoundException {

        List<T> beans = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(clazz)
                .withSeparator(',')
                .build()
                .parse();

        if (beans.isEmpty()) {
            throw new FileNotFoundException("Input file is empty: " + fileName);
        }
        return beans;
    }
}
