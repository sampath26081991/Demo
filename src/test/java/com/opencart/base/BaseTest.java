package com.opencart.base;

import com.opencart.utils.ConfigReader;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.opencart.utils.ExcelUtils;
import com.opencart.utils.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import com.opencart.pages.AgencySelectionPage;
import com.opencart.pages.LoginPage;

import java.lang.reflect.Method;
import java.time.Duration;

import com.opencart.utils.DebuggerUtil;

public class BaseTest {

    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;

    // ===== REPORT SETUP =====
    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    // ===== DRIVER SETUP =====
    @BeforeMethod
    public void setup(Method method) {

        // If requested via -DwaitForDebugger=true, pause here to let the IDE debugger attach
        DebuggerUtil.waitForDebuggerIfRequested(60);

        test = extent.createTest(method.getName());

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Integer.parseInt(ConfigReader.get("implicitWait")))
        );

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driver.get(ConfigReader.get("baseUrl"));

        test.pass("Browser launched and URL opened");

        ExtentManager.setTest(
                ExtentManager.getExtent()
                        .createTest("Test: " + getClass().getSimpleName())
        );

        System.out.println("Title: " + driver.getTitle());
        loginToApplication();
    }

    public void loginToApplication() {

        String[][] logins =
                ExcelUtils.getData(
                        "testdata/TestData.xlsx",
                        "Logins"
                );

        String username = logins[0][0];
        String password = logins[0][1];

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.closePopupIfPresent();

        loginPage.login(username, password);

        System.out.println("Login Successful");

        AgencySelectionPage agencyPage =
                new AgencySelectionPage(driver);





        agencyPage.selectAgency("Nightingales");

        System.out.println("Agency Selected");
    }




    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            test.pass("Browser closed");
        }
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}