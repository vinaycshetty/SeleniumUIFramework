package com.vs.framework.pages;

import com.vs.framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by : Vinay Shetty
 * on 01-03-2025 at 21:20
 **/
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[contains(@class, 'oxd-button--main')]");

    public void login(String url,String username, String password) {
        driver.get(url);
        enterText(usernameField, username,5);
        enterText(passwordField, password,5);
        click(loginButton,5);
    }
}
