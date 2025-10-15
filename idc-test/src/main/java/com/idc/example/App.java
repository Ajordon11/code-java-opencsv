package com.idc.example;

import java.io.FileNotFoundException;

import com.idc.example.csv.CsvDataObject;
import com.idc.example.csv.CsvLoader;
import com.idc.example.table.DataObject;
import com.idc.example.table.DataRow;
import com.idc.example.table.FormattedTable;
import com.idc.example.table.Table;
import com.idc.example.table.TableExportService;
import com.idc.example.table.TableService;

public class App 
{
    public static void main( String[] args ) {
        CsvLoader csvLoader = new CsvLoader();
        TableService tableService = new TableService();
        TableExportService tableExportService = new TableExportService(tableService);
        String inputFileName;
        
        if (args.length > 0 && args[0] != null) {
            if (!csvLoader.fileExists(args[0])) {
                System.err.println("Input file does not exist: " + args[0]);
                System.out.println("If you want to use default input file, please run the program without any arguments. Exiting...");
                return;
            }
            System.out.println("Loaded input file: " + args[0]);
            inputFileName = args[0];
        } else {
            System.out.println("No input file specified, using default input file");
            inputFileName = "src/main/resources/IDC-data.csv";
        }

        try {
            CsvDataObject tableData = csvLoader.loadDataFromCsv(inputFileName, DataRow.class);
            DataObject data = new DataObject(tableData.getCsvRows());
            Table table = tableService.getParsedTableForQuarter(data, "2010 Q4");
            
            FormattedTable formattedTable = tableExportService.getFormattedTable(table);
            formattedTable.printTable();

        } catch (FileNotFoundException ex) {
            System.err.println("Input file cannot be read or opened: " + ex.getMessage());
        } catch (IllegalAccessException | InstantiationException ex) {
            System.err.println("Unexpected error: " + ex.getMessage());
        }
    }
}
