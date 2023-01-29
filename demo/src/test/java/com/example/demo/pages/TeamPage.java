package com.example.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TeamPage {
    private WebDriver wd;

    public TeamPage(WebDriver wd) {
        PageFactory.initElements(wd, this);
        this.wd = wd;
    }
    @FindBy(xpath = "//h3[@data-value=\"billye@example.com\"]")
    private WebElement billyeEmail;

    @FindBy(xpath = "//ul//a[@id='navbarDropdown']")
    private WebElement avatar;

    public String getEmail() {
        return wd.findElement(By.xpath("//h3[@data-value=\"billye@example.com\"]")).getText();
    }


}
