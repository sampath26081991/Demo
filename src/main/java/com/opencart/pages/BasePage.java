package com.opencart.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor js;

    private final int TIMEOUT = 120;

    public BasePage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);

        wait = new WebDriverWait(driver,
                Duration.ofSeconds(TIMEOUT));

        actions = new Actions(driver);

        js = (JavascriptExecutor) driver;
    }

    // ==============================
    // WAIT METHODS
    // ==============================

    public void waitForVisibility(WebElement element) {

        wait.until(
                ExpectedConditions.visibilityOf(element)
        );
    }

    public void waitForClickability(WebElement element) {

        wait.until(
                ExpectedConditions.elementToBeClickable(element)
        );
    }

    public void waitForInvisibility(WebElement element) {

        wait.until(
                ExpectedConditions.invisibilityOf(element)
        );
    }

    public void waitForPresence(By locator) {

        wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    public void waitForTitleContains(String title) {

        wait.until(
                ExpectedConditions.titleContains(title)
        );
    }

    public void waitForUrlContains(String url) {

        wait.until(
                ExpectedConditions.urlContains(url)
        );
    }

    // ==============================
    // CLICK ACTIONS
    // ==============================

    public void click(WebElement element) {

        waitForClickability(element);

        element.click();
    }

    public void jsClick(WebElement element) {

        js.executeScript(
                "arguments[0].click();",
                element
        );
    }

    public void clickUsingActions(WebElement element) {

        actions.moveToElement(element)
                .click()
                .perform();
    }

    public String waitForElementAndGetText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    // ==============================
    // TEXTBOX ACTIONS
    // ==============================

    public void type(WebElement element,
                     String text) {

        waitForVisibility(element);

        element.clear();

        element.sendKeys(text);
    }

    public void appendText(WebElement element,
                           String text) {

        waitForVisibility(element);

        element.sendKeys(text);
    }

    public void clear(WebElement element) {

        waitForVisibility(element);

        element.clear();
    }

    public String getText(WebElement element) {

        waitForVisibility(element);

        return element.getText();
    }

    public String getAttribute(WebElement element,
                               String attribute) {

        return element.getAttribute(attribute);
    }

    // ==============================
    // DROPDOWN METHODS
    // ==============================

    public void selectByVisibleText(WebElement element,
                                    String text) {

        Select select =
                new Select(element);

        select.selectByVisibleText(text);
    }

    public void selectByValue(WebElement element,
                              String value) {

        Select select =
                new Select(element);

        select.selectByValue(value);
    }

    public void selectByIndex(WebElement element,
                              int index) {

        Select select =
                new Select(element);

        select.selectByIndex(index);
    }

    // ==============================
    // MOUSE ACTIONS
    // ==============================

    public void hover(WebElement element) {

        actions.moveToElement(element)
                .perform();
    }

    public void doubleClick(WebElement element) {

        actions.doubleClick(element)
                .perform();
    }

    public void rightClick(WebElement element) {

        actions.contextClick(element)
                .perform();
    }

    public void dragAndDrop(WebElement source,
                            WebElement target) {

        actions.dragAndDrop(source, target)
                .perform();
    }

    // ==============================
    // KEYBOARD ACTIONS
    // ==============================

    public void pressEnter(WebElement element) {

        element.sendKeys(Keys.ENTER);
    }

    public void pressTab(WebElement element) {

        element.sendKeys(Keys.TAB);
    }

    public void pressEscape(WebElement element) {

        element.sendKeys(Keys.ESCAPE);
    }

    // ==============================
    // SCROLL METHODS
    // ==============================

    public void scrollToElement(WebElement element) {

        js.executeScript(
                "arguments[0].scrollIntoView(true);",
                element
        );
    }

    public void scrollByPixel(int x,
                              int y) {

        js.executeScript(
                "window.scrollBy(arguments[0],arguments[1])",
                x,
                y
        );
    }

    public void scrollToBottom() {

        js.executeScript(
                "window.scrollTo(0, document.body.scrollHeight)"
        );
    }

    public void scrollToTop() {

        js.executeScript(
                "window.scrollTo(0, 0)"
        );
    }

    // ==============================
    // ALERT METHODS
    // ==============================

    public void acceptAlert() {

        wait.until(
                ExpectedConditions.alertIsPresent()
        );

        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {

        wait.until(
                ExpectedConditions.alertIsPresent()
        );

        driver.switchTo().alert().dismiss();
    }

    public String getAlertText() {

        wait.until(
                ExpectedConditions.alertIsPresent()
        );

        return driver.switchTo()
                .alert()
                .getText();
    }

    public void handleAlertIfPresent() {

        try {

            Alert alert =
                    driver.switchTo().alert();

            System.out.println(
                    "Alert: " + alert.getText());

            alert.dismiss();

        } catch (NoAlertPresentException e) {

            System.out.println(
                    "No alert present");
        }
    }

    // ==============================
    // FRAME METHODS
    // ==============================

    public void switchToFrame(WebElement element) {

        driver.switchTo().frame(element);
    }

    public void switchToFrame(String nameOrId) {

        driver.switchTo().frame(nameOrId);
    }

    public void switchToDefaultContent() {

        driver.switchTo().defaultContent();
    }

    // ==============================
    // WINDOW METHODS
    // ==============================

    public void switchToWindow(int index) {

        Set<String> windows =
                driver.getWindowHandles();

        String[] windowArray =
                windows.toArray(new String[0]);

        driver.switchTo()
                .window(windowArray[index]);
    }

    public String getCurrentWindowTitle() {

        return driver.getTitle();
    }

    // ==============================
    // ELEMENT VALIDATIONS
    // ==============================

    public boolean isDisplayed(WebElement element) {

        try {

            waitForVisibility(element);

            return element.isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public boolean isEnabled(WebElement element) {

        return element.isEnabled();
    }

    public boolean isSelected(WebElement element) {

        return element.isSelected();
    }

    // ==============================
    // LIST METHODS
    // ==============================

    public int getElementCount(List<WebElement> elements) {

        return elements.size();
    }

    // ==============================
    // BROWSER METHODS
    // ==============================

    public void refreshPage() {

        driver.navigate().refresh();
    }

    public void navigateBack() {

        driver.navigate().back();
    }

    public void navigateForward() {

        driver.navigate().forward();
    }

    public String getPageTitle() {

        return driver.getTitle();
    }

    public String getCurrentUrl() {

        return driver.getCurrentUrl();
    }

    // ==============================
    // JAVASCRIPT METHODS
    // ==============================

    public void highlightElement(WebElement element) {

        js.executeScript(
                "arguments[0].style.border='3px solid red'",
                element
        );
    }

    public void zoomPage(String percentage) {

        js.executeScript(
                "document.body.style.zoom='" +
                        percentage + "'"
        );
    }

    // ==============================
    // STATIC WAIT
    // ==============================

    public void hardWait(int seconds) {

        try {

            Thread.sleep(seconds * 1000L);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }
}