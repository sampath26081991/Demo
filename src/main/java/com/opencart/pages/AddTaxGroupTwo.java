package com.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class AddTaxGroupTwo extends BasePage{
    public AddTaxGroupTwo(WebDriver driver) {
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

    // Locator for checkboxes
    private By checkboxLocator = By.xpath("//input[@type='checkbox']");

    public void createTaxGroup(String tgName, String tgDescription, String tgStartDate, String tgEndDate) {
        type(taxGroupName, tgName);
        type(description, tgDescription);
        type(effectiveStartDate, tgStartDate);
        type(effectiveEndDate, tgEndDate);
        hardWait(5);

        // Scroll to Select All button to make it visible
        scrollToElement(selectAllButton);
        hardWait(2);

        // Wait for visibility
        waitForVisibility(selectAllButton);
        hardWait(2);

        // Scroll down a bit more to see the checkboxes below
        scrollByPixel(0, 300);
        hardWait(2);

        // Use JavaScript click for more reliable clicking
        jsClick(selectAllButton);
        System.out.println("Select All button clicked using JavaScript");
        hardWait(5);

        // Verify checkboxes are selected by checking if they have checked attribute
        List<WebElement> checkboxes = driver.findElements(checkboxLocator);
        System.out.println("Total checkboxes found: " + checkboxes.size());

        int selectedCount = 0;
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                selectedCount++;
            }
        }
        System.out.println("Checkboxes selected: " + selectedCount);

        // Scroll down to see Save button
        scrollByPixel(0, 300);
        hardWait(2);

        // Wait for Save button to be clickable
        waitForClickability(saveTaxGroupButton);
        hardWait(1);

        // Click Save button using JavaScript for reliability
        jsClick(saveTaxGroupButton);
        System.out.println("Save Tax Group button clicked");
    }
}
