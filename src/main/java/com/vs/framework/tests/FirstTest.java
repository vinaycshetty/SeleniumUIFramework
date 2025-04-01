package com.vs.framework.tests;

import com.vs.framework.base.BaseTest;
import com.vs.framework.pages.LoginPage;
import com.vs.framework.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sikuli.script.FindFailed;
import org.testng.annotations.Test;

/**
 * Created by : Vinay Shetty
 * on 25-02-2025 at 19:15
 **/
public class FirstTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(FirstTest.class);
    private static ConfigReader configReader;

    @Test
    public void testLogin() throws FindFailed, InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        configReader = new ConfigReader("src/main/resources/config.properties");
        String url = configReader.getProperty("baseUrl");
        String username = configReader.getProperty("username");
        String password = configReader.getProperty("password");
        loginPage.login(url,username,password);
        log.info("✅ Login test executed successfully!");
    }

}
