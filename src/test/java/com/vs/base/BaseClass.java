package com.vs.base;
import com.vs.framework.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Created by : Vinay Shetty
 * on 25-02-2025 at 22:15
 **/
public class BaseClass {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected FluentWait<WebDriver> fluentWait;
    private static final Logger logger = LogManager.getLogger(BaseClass.class);

    @BeforeClass
    public void setUp(){
        logger.info("Starting Test Execution...");

        // Load configurations
        String browser = ConfigReader.getProperty("browser");
        String baseUrl = ConfigReader.getProperty("baseUrl");
        int implicitWait = Integer.parseInt(ConfigReader.getProperty("implicitWait"));
        int explicitWait = Integer.parseInt(ConfigReader.getProperty("explicitWait"));
        int fluentWaitPolling = Integer.parseInt(ConfigReader.getProperty("fluentWaitPolling"));

        // Browser setup
        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(explicitWait))
                .pollingEvery(Duration.ofMillis(fluentWaitPolling))
                .ignoring(Exception.class);

        driver.get(baseUrl);
        logger.info("Navigated to: " + baseUrl);
    }

    protected void waitForElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void fluentWaitForElement(By locator) {
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
            logger.info("Test Execution Completed.");
        }
    }
}
