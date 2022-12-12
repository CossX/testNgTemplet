package com.example.demo;

import com.example.demo.pages.AfterLoginPage;
import com.example.demo.pages.LoginPage;
import com.example.demo.utility.DataProviders;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class AuthWithDataProviderTest {
    WebDriver wd;
    LoginPage loginPage;
    AfterLoginPage afterLoginPage;

    private final String URL = "https://derrick686.softr.app/login";

    @BeforeClass(groups = "Provider")
    public void prepare() {
        wd = new ChromeDriver();
        loginPage = new LoginPage(wd);
        afterLoginPage = new AfterLoginPage(wd);
        wd.get(URL);
        wd.manage().window().maximize();
    }

    @AfterClass
    public void after() throws InterruptedException {
        wd.close();
        wd.quit();
        Thread.sleep(2000);
    }

    @Test(dataProvider = "getCredsFromCSV", dataProviderClass = DataProviders.class)
    public void authTest(String email, String password, String text) {
        loginPage.getAuth(email, password);
        Assert.assertEquals(afterLoginPage.getAvatarText(), text,
                "The text of manager avatar does not match expected");}

    @Test(dataProvider = "getWrongCredsFromCSV", dataProviderClass = DataProviders.class, groups = "Provider")
    public void shouldBeErrorMessage(String email, String password, String text) {
        loginPage.getAuth(email, password);
        Assert.assertEquals(loginPage.getErrorText(), text,
                "The error message does not match expected");}
}
