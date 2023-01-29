package com.example.demo;

import com.example.demo.pages.AfterLoginPage;
import com.example.demo.pages.LoginPage;
import com.example.demo.pages.TeamPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class AuthTest {
    private WebDriver wd;
    private LoginPage loginPage;

    private TeamPage teamPage;

    private AfterLoginPage afterLoginPage;

    private final String URL = "https://derrick686.softr.app/login";
    private final String MANAGER_EMAIL = "billye@example.com";
    private final String CLIENT_EMAIL = "lucie@example.com";
    private final String CONSULTANT_EMAIL = "edra@example.com";
    private final String WRONG_EMAIL = "wrongemail@example.com";

    private final String CORRECT_PASSWORD = "123456";
    private final String WRONG_PASSWORD = "qw12odld";

    private final String MANAGER_AVATAR_TEXT = "BH";
    private final String CLIENT_AVATAR_TEXT = "LC";
    private final String CONSULTANT_AVATAR_TEXT = "EA";
    private final String ERROR_MESSAGE_TEXT = "Invalid email or password";

    @BeforeMethod
    public void prepare(Method m, Object[] p) {
        wd = new ChromeDriver();
        loginPage = new LoginPage(wd);
        afterLoginPage = new AfterLoginPage(wd);
        teamPage = new TeamPage(wd);
        wd.get(URL);
        wd.manage().window().maximize();

    }

    @AfterMethod
    public void after() throws InterruptedException {
        wd.close();
        wd.quit();
        sleep(2000);
    }

    @Test
    public void authManagerTest() {
        loginPage.getAuth(MANAGER_EMAIL, CORRECT_PASSWORD);
        Assert.assertEquals(afterLoginPage.getAvatarText(), MANAGER_AVATAR_TEXT,
                "The text of manager avatar does not match expected");
    }

    @Test
    public void authClientTest() {
        loginPage.getAuth(CLIENT_EMAIL, CORRECT_PASSWORD);
        Assert.assertEquals(afterLoginPage.getAvatarText(), CLIENT_AVATAR_TEXT,
                "The text of client avatar does not match expected");
    }

    @Test
    public void authConsultantTest() {
        loginPage.getAuth(CONSULTANT_EMAIL, CORRECT_PASSWORD);
        Assert.assertEquals(afterLoginPage.getAvatarText(), CONSULTANT_AVATAR_TEXT,
                "The text of consultant avatar does not match expected");
    }

    @Test
    public void shouldBeErrorMessageWithWrongPassword() {
        loginPage.getAuth(CONSULTANT_EMAIL, WRONG_PASSWORD);
        Assert.assertEquals(loginPage.getErrorText(), ERROR_MESSAGE_TEXT,
                "The error message does not match expected");
    }

    @Test
    public void shouldBeErrorMessageWithWrongEmail() {
        loginPage.getAuth(WRONG_EMAIL, CORRECT_PASSWORD);
        Assert.assertEquals(loginPage.getErrorText(), ERROR_MESSAGE_TEXT,
                "The error message does not match expected");
    }

    @Test
    public void emailMatcher() throws InterruptedException {
        loginPage.getAuth(MANAGER_EMAIL, CORRECT_PASSWORD);
        sleep(500);
        afterLoginPage.teamLinkClick();
        sleep(500);
        String email = teamPage.getEmail();
        Assert.assertTrue(email.matches("[a-z0-9]+@[a-z0-9]+\\.de"));
//        billye@example.com


    }

}
