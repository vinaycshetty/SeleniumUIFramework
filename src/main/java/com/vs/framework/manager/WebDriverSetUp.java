package com.vs.framework.manager;

import com.vs.framework.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by : Vinay Shetty
 * on 01-03-2025 at 20:20
 **/
public class WebDriverSetUp {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ConfigReader configReader;

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("Driver is not initialized. Call setupDriver() first.");
        }
        return driver.get();
    }

    public static void setupDriver(String configFilePath) {
        if (driver.get() != null) {
            return; // Driver already initialized
        }

        configReader = new ConfigReader(configFilePath);
        String browser = configReader.getProperty("browser");

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.get().manage().window().maximize();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

}
