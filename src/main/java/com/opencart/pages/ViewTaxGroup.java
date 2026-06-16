package com.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
public class ViewTaxGroup extends BasePage {

    public ViewTaxGroup(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//tr[td/b[normalize-space()='june2026']]/td[2]/b")
    private WebElement tgNameView;

    @FindBy(xpath = "//tr[td/b[normalize-space()='june2026']]//td[@class='description-text']")
    private WebElement tgDescriptionView;

    @FindBy(xpath = "//tr[td/b[normalize-space()='june2026']]//td[4]")
    private WebElement tgDatesView;

    @FindBy(xpath = "//tr[td/b[normalize-space()='june2026']]//span[contains(@class,'badge--status')]")
    private WebElement tgStatusView;


    public void validateTaxGroupDetails(String expectedName,
                                        String expectedDescription,
                                        String expectedStartDate,
                                        String expectedEndDate,
                                        String expectedStatus) {
        hardWait(5);
        waitForVisibility(tgNameView);
        String actualName = tgNameView.getText().trim();
        waitForVisibility(tgDescriptionView);
        String actualDescription = tgDescriptionView.getText().trim();
        waitForVisibility(tgDatesView);
        String actualDates = tgDatesView.getText().trim();
        waitForVisibility(tgStatusView);
        String actualStatus = tgStatusView.getText().trim();

        System.out.println("Actual Name       : " + actualName);
        System.out.println("Actual Description: " + actualDescription);
        System.out.println("Actual Dates      : " + actualDates);
        System.out.println("Actual Status     : " + actualStatus);

        Assert.assertEquals(actualName, expectedName, "Name mismatch");
        Assert.assertEquals(actualDescription, expectedDescription, "Description mismatch");
        Assert.assertTrue(actualDates.contains(expectedStartDate), "Start Date mismatch");
        Assert.assertTrue(actualDates.contains(expectedEndDate), "End Date mismatch");
        Assert.assertEquals(actualStatus, expectedStatus, "Status mismatch");

        System.out.println("Tax Group Validation Passed");


    }
}