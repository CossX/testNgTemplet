package com.example.demo;

import com.example.demo.pages.AfterLoginPage;
import com.example.demo.pages.LoginPage;
import com.example.demo.utility.DataExcelCsvProviders;
import com.example.demo.utility.DataProviders;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AuthWithExcelCsvDataProviderTest {
    WebDriver wd;

    LoginPage loginPage;
    AfterLoginPage afterLoginPage;


    private final String URL = "https://derrick686.softr.app/login";

    private static final ThreadLocal<WebDriver> WEBDRIVER_THREADLOCAL = new ThreadLocal<WebDriver>();

    @BeforeMethod
    public void prepare() {
        wd = new ChromeDriver();
        loginPage = new LoginPage(wd);
        afterLoginPage = new AfterLoginPage(wd);
        wd.get(URL);
        wd.manage().window().maximize();
    }

    @AfterMethod
    public void after() throws InterruptedException {
        wd.close();
        wd.quit();
        Thread.sleep(2000);
    }

    @Test(dataProvider = "csvDataRead", dataProviderClass = DataExcelCsvProviders.class)
    public void authTest(String email, String password, String text) {
        loginPage.getAuth(email, password);
        Assert.assertEquals(afterLoginPage.getAvatarText(), text,
                "The text of manager avatar does not match expected");
    }

    @Test(dataProvider = "csvWrongDataRead", dataProviderClass = DataExcelCsvProviders.class)
    public void shouldBeErrorMessage(String email, String password, String text) {
        loginPage.getAuth(email, password);
        Assert.assertEquals(loginPage.getErrorText(), text,
                "The error message does not match expected");
    }

    @Test(dataProvider = "excelSheetDataRead", dataProviderClass = DataExcelCsvProviders.class)
    public void authDataFromExcelTest(String email, String password, String text) {
        loginPage.getAuth(email, password);
        Assert.assertEquals(afterLoginPage.getAvatarText(), text,
                "The text of manager avatar does not match expected");
    }

    @Test(dataProvider = "excelSheetWrongDataRead", dataProviderClass = DataExcelCsvProviders.class)
    public void authWrongDataFromExcelTest(String email, String password, String text) {
        loginPage.getAuth(email, password);
        Assert.assertEquals(loginPage.getErrorText(), text,
                "The text of manager avatar does not match expected");
    }
}
