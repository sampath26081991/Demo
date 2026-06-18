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

    @FindBy(xpath = "//*[name()='svg' and contains(@class,'w-5')]")
    private WebElement closePopup;

    // Added login helper that reuses BasePage actions (type, click)
    public void login(String username, String password) {
        hardWait(5);
        // enter username
        type(usernameField, username);
        // enter password
        type(passwordField, password);
        click(loginButton);
    }

    public void closePopupIfPresent() {

        try {
            if (closePopup.isDisplayed()) {
                closePopup.click();
            }
        } catch (Exception e) {
            System.out.println("Popup not displayed");
        }
    }

}

