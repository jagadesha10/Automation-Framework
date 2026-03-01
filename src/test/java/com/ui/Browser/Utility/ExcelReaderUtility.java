package com.ui.Browser.Utility;

import com.ui.pojo.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtility {

    public static Iterator<User> readExcelFile(String filename) {

        List<User> userList = new ArrayList<>();

        String filePath = System.getProperty("user.dir")
                + "\\testData\\"
                + filename;

        System.out.println("Reading Excel from: " + filePath);

        try {

            InputStream fis = new FileInputStream(filePath);

            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            XSSFSheet sheet = workbook.getSheet("LoginTestData");

            Iterator<Row> rowIterator = sheet.iterator();

            // skip header
            if (rowIterator.hasNext())
                rowIterator.next();

            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();

                String email = row.getCell(0).getStringCellValue();

                String password = row.getCell(1).getStringCellValue();

                userList.add(new User(email, password));
            }

            workbook.close();
            fis.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return userList.iterator();
    }
}