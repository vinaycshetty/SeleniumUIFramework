package com.vs.framework.base;

import com.vs.framework.manager.WebDriverSetUp;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

/**
 * Created by : Vinay Shetty
 * on 01-03-2025 at 20:32
 **/
public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    @Parameters({"configFile"})
    public void setUp(String configFile) {
        WebDriverSetUp.setupDriver(configFile);
        driver = WebDriverSetUp.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        WebDriverSetUp.quitDriver();
    }
}
