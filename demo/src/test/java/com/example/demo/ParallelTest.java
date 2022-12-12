package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ParallelTest {
    WebDriver driver;
    @AfterClass
    public void after() {
        driver.quit();
    }

    @Test
    public void FirefoxTest() {
//Initializing the firefox driver (Gecko)
        System.out.println("The thread ID for Firefox is "+ Thread.currentThread().getId());
        driver = new FirefoxDriver();
        driver.get("https://www.google.ru/");
        driver.quit();

    }

    @Test
    public void ChromeTest()
    {
        //Initialize the chrome driver
        System.out.println("The thread ID for Chrome is "+ Thread.currentThread().getId());
        driver = new ChromeDriver();
        driver.get("https://ya.ru/");
        driver.quit();
    }
}
