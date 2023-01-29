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

    @FindBy(xpath = "//li[contains(@class,\"nav-item\")]//a[contains(@class,\"sw-font-size-xs sw-text-color-ffffff sw-font-family-default sw-font-weight-semibold sw-letter-spacing-widest sw-display-flex sw-padding-top-6xs sw-padding-bottom-6xs sw-padding-left-6xs sw-padding-right-xs hover:sw-text-decoration-no-underline active:sw-background-color-transparent\")][normalize-space()=\"CLIENTS\"]")
    private WebElement clientLink;

    public String getAvatarText() {
        new WebDriverWait(wd, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(avatar));
        return avatar.getText();
    }

    public void teamLinkClick() {
        new WebDriverWait(wd, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(avatar));
        clientLink.click();
    }

}
