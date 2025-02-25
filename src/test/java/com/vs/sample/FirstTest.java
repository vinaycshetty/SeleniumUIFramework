package com.vs.sample;

import com.vs.base.BaseClass;
import com.vs.framework.utils.WaitUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Created by : Vinay Shetty
 * on 25-02-2025 at 19:15
 **/
public class FirstTest extends BaseClass{
    private static final Logger log = LogManager.getLogger(FirstTest.class);
    @Test
    public void login(){

        WebElement username = WaitUtils.waitForElementVisibility(driver,By.name("username"),10);
        username.sendKeys("Admin");
        WebElement password  = WaitUtils.waitForElementVisibility(driver,By.name("password"),10);
        password.sendKeys("admin123");
        log.info("🔍 Verifying user credentials...");
        WebElement submit = WaitUtils.waitForElementToBeClickable(driver,By.xpath("//button[contains(@class, 'oxd-button--main')]"),10);
        submit.click();
        Boolean pageTitle = WaitUtils.waitForTitleToContain(driver,"OrangeHRM",10);
        Assert.assertTrue(pageTitle, "Title does not contain expected text");
        log.info("✅ Login Successful!");
    }

}
