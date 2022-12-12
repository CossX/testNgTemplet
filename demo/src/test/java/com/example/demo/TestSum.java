package com.example.demo;

import com.example.demo.utility.DataExcelCsvProviders;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestSum {

    @Test(dataProvider = "sumDataRead", dataProviderClass = DataExcelCsvProviders.class)
    public void sumTest(String a, String b, String c) {
        System.out.println(Thread.currentThread().getId());
        Sum sum = new Sum();
        Assert.assertEquals(sum.getSum(Integer.parseInt(a), Integer.parseInt(b)), Integer.parseInt(c), "Сумма не равна ожидаемой");
    }

    @Test
    @Parameters({"a", "b", "c"} )
    public void sumTestParam(String a, String b, String c) {
        Sum sum = new Sum();
        Assert.assertEquals(sum.getSum(Integer.parseInt(a), Integer.parseInt(b)), Integer.parseInt(c), "Сумма не равна ожидаемой");
    }

}
