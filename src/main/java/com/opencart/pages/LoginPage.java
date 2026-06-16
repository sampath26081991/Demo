package com.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {

        super(driver);
    }

    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "btnLogin")
    WebElement loginButton;

    // Added login helper that reuses BasePage actions (type, click)
    public void login(String username, String password) {
        // enter username
        type(usernameField, username);
        // enter password
        type(passwordField, password);
        click(loginButton);
    }

}

