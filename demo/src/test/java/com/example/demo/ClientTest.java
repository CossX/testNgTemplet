package com.example.demo;

import com.example.demo.pages.AfterLoginPage;
import com.example.demo.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class ClientTest {
    private WebDriver wd;
    private LoginPage loginPage;
    private AfterLoginPage afterLoginPage;

    private final String URL = "https://derrick686.softr.app/login";
    private final String CLIENT_EMAIL = "lucie@example.com";

    private final String CORRECT_PASSWORD = "123456";

    private final String CLIENT_AVATAR_TEXT = "LC";

//    @BeforeMethod
//    public void prepare(Method m, Object[] p) {
//        wd = new ChromeDriver();
//        loginPage = new LoginPage(wd);
//        afterLoginPage = new AfterLoginPage(wd);
//        wd.get(URL);
//        wd.manage().window().maximize();
//
//    }

    @BeforeMethod
    @Parameters( {"BrowserType"} )
    public void testExamplePageOnMultipleBrowsers (String browserType) {
        if (browserType.equalsIgnoreCase("Chrome")) {
            wd = new ChromeDriver();
        }
        else if (browserType.equalsIgnoreCase("Firefox")) {
            wd =  new FirefoxDriver();
        }

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

    @Test(priority = 1)
//    @Test
    public void authClientTest() {
        loginPage.getAuth(CLIENT_EMAIL, CORRECT_PASSWORD);
        Assert.assertEquals(afterLoginPage.getAvatarText(), CLIENT_AVATAR_TEXT,
                "The text of client avatar does not match expected");
    }

    @Test(dependsOnMethods = "authClientTest")
//    @Test
    public void clientTest() {
        loginPage.getAuth(CLIENT_EMAIL, CORRECT_PASSWORD);
        Assert.assertTrue(loginPage.shouldBeVisibleServiceLink());

    }

    @Test(dependsOnMethods = "authClientTest")
    //    @Test
    public void clientTest2() {
        loginPage.getAuth(CLIENT_EMAIL, CORRECT_PASSWORD);
        Assert.assertTrue(loginPage.shouldBeOpenWindow());

    }

    @Test(dependsOnMethods = "authClientTest")
    //    @Test
    public void clientTest3() {
        loginPage.getAuth(CLIENT_EMAIL, CORRECT_PASSWORD);
        Assert.assertEquals(loginPage.getHeaderText(), "Content Writing - May 2021");

    }

}
