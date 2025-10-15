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