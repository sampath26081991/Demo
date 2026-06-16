package com.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Page object for agency selection screen.
 */
public class AgencySelectionPage extends BasePage {

    public AgencySelectionPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (id = "agency")
    private WebElement agencyDropdown;

    @FindBy(id="btnLogin")
    private WebElement loginButton;

    public void selectAgency(String agencyName) {

        click(agencyDropdown);

        waitForPresence(By.xpath( "//span[@class='ng-option-label' and normalize-space()='"
                + agencyName + "']"));

        WebElement agencyOption = driver.findElement(
                By.xpath("//span[@class='ng-option-label' and text()='" + agencyName + "']")
        );

        click(agencyOption);
        click(loginButton);

    }

}
