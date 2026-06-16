package com.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Navigation helper to reach the Taxes tab via Administration -> Masters -> Taxes
 */
public class NavigationToTax extends BasePage {

    public NavigationToTax(WebDriver driver) {

        super(driver);
    }

    @FindBy(xpath ="//span[normalize-space()='Administration']/ancestor::a")
    private WebElement administrationMenu;

    @FindBy(xpath = "//a[@href='#/administration/masters']")
    private WebElement mastersMenu;

    @FindBy(xpath = "//a[@href='#/administration/masters/taxes']")
    private WebElement taxesMenu;

    @FindBy(xpath = "//span[normalize-space()='Tax Master']")
    private WebElement taxMasterText;

    @FindBy(xpath = "//span[normalize-space()='Tax Groups']")
    private WebElement taxGroupsMenu;

    @FindBy(xpath = "//button[@aria-label='add tax type']")
    private WebElement addTaxGroupButton;

    public void clickAdministrationMenu() {

        waitForVisibility(administrationMenu);
       click(administrationMenu);
       waitForVisibility(mastersMenu);
       click(mastersMenu);
         waitForVisibility(taxesMenu);
        clickUsingActions(taxesMenu);


    }

    public void getTaxMasterText() {
        waitForElementAndGetText(taxMasterText);
        hardWait(5);

    }

    public void clickTaxGroupsMenu() {
        waitForVisibility(taxGroupsMenu);
        clickUsingActions(taxGroupsMenu);
        hardWait(5);
        click(addTaxGroupButton);
    }

    public void clickAddTaxGroupButton() {
        click(addTaxGroupButton);
    }
}

