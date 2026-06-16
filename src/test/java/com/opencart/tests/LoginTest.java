
package com.opencart.tests;

import com.opencart.base.BaseTest;
import com.opencart.pages.*;
import com.opencart.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.Test;



public class LoginTest extends BaseTest {

    @Test
    public void verifyLogin() {

//        String[][] logins =
//                ExcelUtils.getData(
//                        "testdata/TestData.xlsx",
//                        "Logins");
//
//        String username = logins[0][0];
//        String password = logins[0][1];
//
//        LoginPage loginPage = new LoginPage(driver);
//
//        // Credentials are inlined here per request — replace with real values
//
//
//
//
//
//        //String before = driver.getCurrentUrl();
//
//        loginPage.login(username, password);
//        System.out.println("Login button clicked");
//        // after login, select agency and continue
//
//        AgencySelectionPage agencyPage =
//                new AgencySelectionPage(driver);
//
//        agencyPage.selectAgency("Nightingales");
//        System.out.println("Home page is displayed");

        NavigationToTax taxPage = new NavigationToTax(driver);
        taxPage.clickAdministrationMenu();
        taxPage.getTaxMasterText();
        System.out.println("Tax master page is displayed");
        taxPage.clickTaxGroupsMenu();
        taxPage.clickAddTaxGroupButton();
        String[][] taxGroups =
                ExcelUtils.getData(
                        "testdata/TestData.xlsx",
                        "TaxGroups");
        AddTaxGroup addTaxGroup = new AddTaxGroup(driver);
        for (int i = 0; i < taxGroups.length; i++) {

            if (i > 0) {

                NavigationToTax taxPage2 = new NavigationToTax(driver);

                taxPage2.clickAddTaxGroupButton(); // method that opens Add Tax Group page
            }

            String tgName = taxGroups[i][0];
            String tgDescription = taxGroups[i][1];
            String tgStartDate = taxGroups[i][2];
            String tgEndDate = taxGroups[i][3];

            System.out.println(
                    "Creating Tax Group : " + tgName);

            addTaxGroup.createTaxGroup(
                    tgName,
                    tgDescription,
                    tgStartDate,
                    tgEndDate);

            System.out.println(
                    "Created Tax Group : " + tgName);
        }

//        ViewTaxGroup viewTaxGroup = new ViewTaxGroup(driver);
//
//        viewTaxGroup.validateTaxGroupDetails(
//                tgName,
//                tgDescription,
//                tgStartDate,
//                tgEndDate,
//                "Active");
//
//        System.out.println("Tax Group Validation Passed");

    }

}
