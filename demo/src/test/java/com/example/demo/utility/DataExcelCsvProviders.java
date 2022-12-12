package com.example.demo.utility;

import com.github.hemanthsridhar.CSVUtils;
import com.github.hemanthsridhar.ExcelUtils;
import com.github.hemanthsridhar.lib.ExtUtils;
import org.testng.annotations.DataProvider;

public class DataExcelCsvProviders {
    @DataProvider
    public Object[][] csvDataRead() throws Exception {
        String path = "src/test/resources/data.csv";
        ExtUtils ext = new CSVUtils(path, true);
        return ext.parseData();
    }

    @DataProvider
    public Object[][] csvWrongDataRead() throws Exception {
        String path = "src/test/resources/dataWrong.csv";
        ExtUtils ext = new CSVUtils(path, true);
        return ext.parseData();
    }

    @DataProvider
    public Object[][] excelSheetDataRead() throws Exception {
        ExtUtils ext = new ExcelUtils("src/test/resources/allData.xlsx", "correctData");
        return ext.parseData();
    }

    @DataProvider
    public Object[][] excelSheetWrongDataRead() throws Exception {
        ExtUtils ext = new ExcelUtils("src/test/resources/allData.xlsx", "wrongData");
        return ext.parseData();
    }

    @DataProvider(parallel = true)
    public Object[][] sumDataRead() throws Exception {
        String path = "src/test/resources/sumData.csv";
        ExtUtils ext = new CSVUtils(path, true);
        return ext.parseData();
    }
}
