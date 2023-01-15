package org.example.excelData;

public interface DataSource {

    void createConnection() throws Exception;

    String getDataFromExcel(String rowName, String colName) throws Exception;


    void updateStatus(String rowName, boolean status) throws Exception;
}
