package com.opencart.utils;

import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;

public class ExcelUtils {

    public static String[][] getData(String filePath,
                                     String sheetName) {

        String[][] data = null;

        try {

            FileInputStream fis =
                    new FileInputStream(filePath);

            XSSFWorkbook workbook =
                    new XSSFWorkbook(fis);

            XSSFSheet sheet =
                    workbook.getSheet(sheetName);

            int rowCount =
                    sheet.getLastRowNum();

            int colCount =
                    sheet.getRow(0).getLastCellNum();

            data = new String[rowCount][colCount];

            for (int i = 1; i <= rowCount; i++) {

                XSSFRow row = sheet.getRow(i);

                for (int j = 0; j < colCount; j++) {

                    XSSFCell cell = row.getCell(j);

                    data[i - 1][j] = cell.toString();
                }
            }

            workbook.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}