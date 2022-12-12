package com.example.demo.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver wd;

    public LoginPage(WebDriver wd) {
        PageFactory.initElements(wd, this);
        this.wd = wd;
    }
    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    @FindBy(id = "sw-sign-in-submit-btn")
    private WebElement signButton;

    @FindBy(xpath = "//div[@class='error-message login-error d-block']")
    private WebElement errorArea;

    @FindBy(xpath = "//a[normalize-space()='OUR SERVICES']")
    private WebElement serviceLink;

    @FindBy(xpath = "//div[contains(@data-type,'tag')]//span[contains(text(),'Open')]")
    private WebElement openButton;

    @FindBy(xpath = "//div[contains(@class,'js-list-item d-flex justify-content-center flex-column box position-relative sw-background-color-ffffff sw-margin-bottom-4xs sw-border-style-none sw-border-width-none sw-border-color-a5a5a5 sw-border-radius-2xl sw-box-shadow-l hover:sw-box-shadow-2xl sw-padding-top-xs sw-padding-bottom-xs sw-padding-left-xs sw-padding-right-xs sw-cursor-default active')]")
    private WebElement window;

    @FindBy(xpath = "//h3[@data-value=\"Content Writing - May 2021\"]")
    private WebElement header;

    public void getAuth(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        Actions actions = new Actions(wd);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        new WebDriverWait(wd, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(signButton));
        signButton.click();
    }

    public String getErrorText() {
        new WebDriverWait(wd, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(errorArea));
        return errorArea.getText();
    }

    public Boolean shouldBeVisibleServiceLink() {
        new WebDriverWait(wd, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(serviceLink));
        return serviceLink.isDisplayed();
    }

    public Boolean shouldBeOpenWindow() {
        new WebDriverWait(wd, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(openButton));
        openButton.click();
        return window.isDisplayed();
    }

    public String getHeaderText() {
        new WebDriverWait(wd, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOf(header));
        return header.getText();
    }

}
