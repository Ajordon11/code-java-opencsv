# idc-test

Example code using Java SE (21) and [OpenCSV](https://opencsv.sourceforge.net/)

## Prerequisites

- Java 21 or higher
- Maven

## Building

To build the project, use the following command:

```bash
mvn clean compile assembly:single
```
## Running

To run the App, use the following command: 

1. using default input csv -> src/main/resources/IDC-data.csv
```bash
java -jar target/idc-test-1.0-SNAPSHOT-jar-with-dependencies.jar
```

2. specifying which csv file to use:
```bash
java -jar target/idc-test-1.0-SNAPSHOT-jar-with-dependencies.jar src/main/resources/IDC-data.csv
```

### Notes
1. Units in CSV file are provided as doubles (with decimal point), but since there is nothing specifying what are those units, I initially assumed that those are thousands delimiters (since it doesn't make sense to sell partial products to me). 
 - the number of units is converted to long, decimal point removed
 - this was implemented in idc-test\src\main\java\com\idc\example\csv\StringToLongConverter.java
 - looking at the actual numbers though, this might have been wrong assumption
 - can be easily changed, but I will keep it like this for now

 2. There were no specifications how this should be used, so most of the "arguments" for program (selected quarter, output file location or even selected country) are set directly in code
  - one exception is CSV input file location, which can be supplied from command line
  - there is no mention of selecting data for table by country, only on the picture, so there is method implemented for that, but it is not explicitly used (in idc-test\src\main\java\com\idc\example\table\DataObject.java)

  3. Export to Excel (.xlsx) could be done without additional dependencies, but it is a bit shady, so I didn't venture there. Much easier would be to implement it using additional libraries (Apache POI, JExcel,...).