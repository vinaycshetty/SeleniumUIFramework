package com.vs.framework.base;

import com.vs.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by : Vinay Shetty
 * on 01-03-2025 at 21:10
 **/
public class BasePage {
    protected WebDriver driver;
    protected WaitUtils waitUtils;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    protected WebElement getElement(By locator, int timeout) {
        return waitUtils.waitForElementVisibility(locator,timeout);
    }

    protected void click(By locator, int timeout) {
        waitUtils.waitForElementToBeClickable(locator,timeout).click();
    }

    protected void enterText(By locator, String text, int timeout) {
        WebElement element = getElement(locator,timeout);
        element.clear();
        element.sendKeys(text);
    }
}
