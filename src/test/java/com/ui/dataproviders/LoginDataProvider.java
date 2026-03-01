package com.ui.dataproviders;

import com.google.gson.Gson;
import com.ui.Browser.Utility.CSVReaderUtility;
import com.ui.Browser.Utility.ExcelReaderUtility;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginDataProvider {

    @DataProvider(name = "LoginTestDataProvider")
    public Iterator<Object[]> loginDataProvider() throws FileNotFoundException {
        Gson gson = new Gson();
        File testDataFile = new File(System.getProperty("user.dir") + "//testData//loginData.json");
        FileReader fileReader = new FileReader(testDataFile);
        TestData data = gson.fromJson(fileReader, TestData.class);

        List<Object[]> dataToReturn = new ArrayList<>();
        for (User user : data.getData()) {

            dataToReturn.add(new Object[]{user});
        }
        return dataToReturn.iterator();
    }

    @DataProvider(name = "LoginTestCSVDataProvider")
    public Iterator<User> loginCSVDataProvider() {
        return CSVReaderUtility.readCSVFile("loginData.csv");
    }

    @DataProvider(name = "LoginTestExcelDataProvider")
    public Iterator<User> loginExcelDataProvider() {
        return ExcelReaderUtility.readExcelFile("LoginData.xlsx");
    }
}
