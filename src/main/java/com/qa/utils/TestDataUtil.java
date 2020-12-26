package com.qa.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class TestDataUtil {

    public static Object[][] readTestData(String sheetName, String testCaseName) {
        System.out.println("TestCaseName::" + testCaseName);
        FileInputStream fi = null;
        Object[][] data = null;
        String FileName = System.getProperty("user.dir") +
                File.separator + "src" + File.separator + "test" +
                File.separator + "Resources" + File.separator +
                "TestData.xlsx";
        try {
            fi = new FileInputStream(FileName);
            XSSFWorkbook workbook = null;
            workbook = new XSSFWorkbook(fi);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            int rowCount = 0;
            for (int k = 1; k <= sheet.getLastRowNum(); k++) {
                if (sheet.getRow(k).getCell(0).getStringCellValue().equals(testCaseName))
                    rowCount++;
            }
            data = new Object[rowCount][sheet.getRow(0).getLastCellNum()];
            int rowIndex = 0;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                if (sheet.getRow(i).getCell(0).getStringCellValue().equals(testCaseName)) {
                    for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                        data[rowIndex][j] = sheet.getRow(i).getCell(j).getStringCellValue();
                    }
                    rowIndex++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
