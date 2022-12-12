package com.example.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AfterLoginPage {
    private WebDriver wd;

    public AfterLoginPage(WebDriver wd) {
        PageFactory.initElements(wd, this);
        this.wd = wd;
    }

    @FindBy(xpath = "//ul//a[@id='navbarDropdown']")
    private WebElement avatar;

    public String getAvatarText() {
        new WebDriverWait(wd, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(avatar));
        return avatar.getText();
    }


}
