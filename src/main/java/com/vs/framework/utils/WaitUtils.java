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

    /**
     * Set Implicit Wait (Applied Globally)
     */
    public static void setImplicitWait(WebDriver driver, int timeout) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

    /**
     * Page load timeout
     */
    public static void setPageLoadTimeOut(WebDriver driver, int timeout) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
    }

    /**
     * set javascript execution timeout
     */
    public static void setScriptTimeOut(WebDriver driver, int timeout) {
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(timeout));
    }

    /**
     * set Explicit wait of all types
     */
    // wait for an element to be present in BOM
    public static WebElement waitForElementPresence(WebDriver driver, By locator, int timeout) {
        try {
            log.info("⌛ Waiting for element presence: " + locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            log.error("❌ Element not found: " + locator + " - Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Wait for an element to be visible.
     */
    public static WebElement waitForElementVisibility(WebDriver driver, By locator, int timeout) {
        try {
            log.info("⌛ Waiting for element visibility: " + locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            log.error("❌ Element not visible: " + locator + " - Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Wait for an element to be clickable.
     */
    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator, int timeout) {
        try {
            log.info("⌛ Waiting for element to be clickable: " + locator);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            log.error("❌ Element not clickable: " + locator + " - Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Wait for the title to contain specific text.
     */
    public static boolean waitForTitleToContain(WebDriver driver, String titleFragment, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.titleContains(titleFragment));
    }

    /**
     * Fluent Wait for an element to be present with polling.
     */
    public static WebElement fluentWaitForElement(WebDriver driver, By locator, int timeout, int pollingTime) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);

        return wait.until(driverInstance -> driverInstance.findElement(locator));
    }

    /**
     * Fluent Wait for an element to be visible.
     */
    public static WebElement fluentWaitForElementVisibility(WebDriver driver, By locator, int timeout, int pollingTime) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Fluent Wait for an element to be clickable.
     */
    public static WebElement fluentWaitForElementToBeClickable(WebDriver driver, By locator, int timeout, int pollingTime) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);

        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Fluent Wait for text to be present in an element.
     */
    public static boolean fluentWaitForTextToBePresent(WebDriver driver, By locator, String text, int timeout, int pollingTime) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);

        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

}
