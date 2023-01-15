package org.example.excelData;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Datatable implements DataSource{

    String path;

    Workbook workbook;

    Sheet sheet;

    FileInputStream fis;


    public Datatable(String path){
        this.path = path;
    }

    public void createConnection() throws Exception {

        File file = new File(path);

        fis = new FileInputStream(file);

        workbook = new HSSFWorkbook(fis);

        sheet = workbook.getSheetAt(0);

    }


    public String getDataFromExcel(String rowName, String colName) throws Exception {

        int rowNum=-1, colNum =-1;

        int totalRows = sheet.getLastRowNum();

        //Row match...
        for (int i=0; i<=totalRows; i++){

            if (sheet.getRow(i).getCell(0).getStringCellValue().trim().equals(rowName)){

                rowNum = i;
                break;
            }

        }

        // Column match

        for (int j=0; j< sheet.getRow(0).getPhysicalNumberOfCells(); j++){

            if (sheet.getRow(0).getCell(j).getStringCellValue().trim().equals(colName)){

                colNum = j;

                break;
            }

        }

        if(rowNum == -1 || colNum == -1){
            throw new Exception("Row & Col match not found");
        }

        String output = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();

        fis.close();

        return output;

    }

    @Override
    public void updateStatus(String rowName, boolean status) throws Exception {

        int rowNum=-1, colNum =3;

        int totalRows = sheet.getLastRowNum();

        //Row match...
        for (int i=0; i<=totalRows; i++){

            if (sheet.getRow(i).getCell(0).getStringCellValue().trim().equals(rowName)){

                rowNum = i;
                break;
            }

        }
        FileOutputStream fos = new FileOutputStream(path);
        sheet.getRow(rowNum).createCell(colNum).setCellValue(status);
        workbook.write(fos);
        fos.close();
        fis.close();

    }
}
