package com.vs.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;

/**
 * Created by : Vinay Shetty
 * on 25-02-2025 at 19:55
 **/
public class WaitUtils {
    private static final Logger log = LogManager.getLogger(WaitUtils.class);

    private static WebDriver driver;
    private static WebDriverWait getWait(int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }
    public WaitUtils(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Set Implicit Wait (Applied Globally)
     */
    public static void setImplicitWait(int timeoutInSeconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutInSeconds));
    }

    /**
     * Page load timeout
     */
    public static void setPageLoadTimeOut(int timeoutInSeconds) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeoutInSeconds));
    }

    /**
     * set javascript execution timeout
     */
    public static void setScriptTimeOut(int timeoutInSeconds) {
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(timeoutInSeconds));
    }

    /**
     * set Explicit wait of all types
     */
    // wait for an element to be present in BOM
    public static WebElement waitForElementPresence(By locator, int timeoutInSeconds) {
        try {
            log.info("⌛ Waiting for element presence: " + locator);
            return getWait(timeoutInSeconds).until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            log.error("❌ Element not found: " + locator + " - Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Wait for an element to be visible.
     */
    public static WebElement waitForElementVisibility(By locator, int timeoutInSeconds) {
        try {
            log.info("⌛ Waiting for element visibility: " + locator);
            return getWait(timeoutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            log.error("❌ Element not visible: " + locator + " - Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Wait for an element to be clickable.
     */
    public static WebElement waitForElementToBeClickable(By locator, int timeoutInSeconds) {
        try {
            log.info("⌛ Waiting for element to be clickable: " + locator);
            return getWait(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            log.error("❌ Element not clickable: " + locator + " - Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Wait for the title to contain specific text.
     */
    public static boolean waitForTitleToContain(String titleFragment, int timeoutInSeconds) {
        return getWait(timeoutInSeconds).until(ExpectedConditions.titleContains(titleFragment));
    }

    /**
     * Fluent Wait for an element to be present with polling.
     */
    public static WebElement fluentWaitForElement(By locator, int timeout, int pollingTime) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);

        return wait.until(driverInstance -> driverInstance.findElement(locator));
    }

    /**
     * Fluent Wait for an element to be visible.
     */
    public static WebElement fluentWaitForElementVisibility(By locator, int timeout, int pollingTime) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Fluent Wait for an element to be clickable.
     */
    public static WebElement fluentWaitForElementToBeClickable(By locator, int timeout, int pollingTime) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);

        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Fluent Wait for text to be present in an element.
     */
    public static boolean fluentWaitForTextToBePresent(By locator, String text, int timeout, int pollingTime) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);

        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

}
