package com.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddTaxGroup extends BasePage{
    public AddTaxGroup(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="taxGroupName")
    private WebElement taxGroupName;

    @FindBy(id="description")
    private WebElement description;

    @FindBy(name = "effectiveStartDate")
    private WebElement effectiveStartDate;

    @FindBy(name = "effectiveEndDate")
    private WebElement effectiveEndDate;

    @FindBy(xpath = "//div[contains(@class,'tax-quick-buttons')]//button[normalize-space()='Select All']")
    private WebElement selectAllButton;

    @FindBy(xpath = "//button[normalize-space()='Save Tax Group']")
    private WebElement saveTaxGroupButton;





    public void createTaxGroup(String tgName, String tgDescription, String tgStartDate, String tgEndDate) {
        hardWait(5);
        type(taxGroupName, tgName);
        type(description, tgDescription);
        type(effectiveStartDate, tgStartDate);
        type(effectiveEndDate, tgEndDate);
        hardWait(5);
        waitForVisibility(selectAllButton);
        click(selectAllButton);
        hardWait(5);
        click(saveTaxGroupButton);
        hardWait(5);
    }




}
