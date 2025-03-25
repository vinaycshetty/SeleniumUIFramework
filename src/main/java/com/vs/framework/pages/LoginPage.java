package com.vs.framework.pages;

import com.vs.framework.base.BasePage;
import com.vs.framework.utils.sikuliUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.io.File;

/**
 * Created by : Vinay Shetty
 * on 01-03-2025 at 21:20
 **/
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By usernameField = By.name("username1");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[contains(@class, 'oxd-button--main')]");

    public void login(String url,String username, String password) throws FindFailed, InterruptedException {
        driver.get(url);
        enterText(passwordField, password,5);
        try {
            enterText(usernameField, username,5);
        } catch (Exception e) {
            File file = new File("usernameImg.png");
            System.out.println(file.exists());
            Screen screen = new Screen();
            Pattern pattern = new Pattern(System.getProperty("user.dir") + "/usernameImg.png");
            screen.wait(pattern,1000);
            screen.click();
            screen.type(username);
            Thread.sleep(5000);
        }
        click(loginButton,5);
    }
}
