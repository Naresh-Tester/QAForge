package com.orangehrm.actiondriver;

import com.orangehrm.utilities.ConfigReader;
import com.orangehrm.utilities.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionDriver {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerUtil.getLogger(ActionDriver.class);

    public ActionDriver(WebDriver driver) {
        this.driver = driver;
        int explicitWait = ConfigReader.getExplicitWait();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
        logger.info("ActionDriver initialized");
    }

    // Wait for element to be clickable
    public void waitForElementToBeClickable(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            logger.error("Element not clickable {} : {}", locator, e.getMessage());
        }
    }

    // Wait for element to be visible
    private void waitForElementToBeVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element not visible {} : {}", locator, e.getMessage());
        }
    }

    // Compare element text with expected text
    public boolean compareElementText(By locator, String expectedText) {
        try {
            String actualText = getText(locator);

            if (actualText.equals(expectedText.trim())) {
                logger.info("Text matched: {}", actualText);
                return true;
            } else {
                logger.error("Text mismatch. Expected: {} Actual: {}", expectedText, actualText);
                return false;
            }

        } catch (Exception e) {
            logger.error("Unable to compare text {} : {}", locator, e.getMessage());
            return false;
        }
    }

    // Wait for element to be invisible
    public void waitForElementToBeInvisible(By locator) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            logger.error("Element still visible {} : {}", locator, e.getMessage());
        }
    }

    // Click element
    public void click(By locator) {
        try {
            logger.info("Clicking element {}", locator);
            waitForElementToBeClickable(locator);
            driver.findElement(locator).click();
        } catch (Exception e) {
            logger.error("Unable to click element {} : {}", locator, e.getMessage());
        }
    }

    // Type text
    public void type(By locator, String text) {
        try {
            waitForElementToBeVisible(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
            logger.info("Entered text into {}", locator);
        } catch (Exception e) {
            logger.error("Unable to type into {} : {}", locator, e.getMessage());
        }
    }

    // Get element text
    public String getText(By locator) {
        try {
            waitForElementToBeVisible(locator);
            return driver.findElement(locator).getText();
        } catch (Exception e) {
            logger.error("Unable to get text from {} : {}", locator, e.getMessage());
            return null;
        }
    }

    // Verify element displayed
    public boolean isElementDisplayed(By locator) {
        try {
            waitForElementToBeVisible(locator);
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            logger.error("Element not displayed {} : {}", locator, e.getMessage());
            return false;
        }
    }

    // Verify element enabled
    public boolean isElementEnabled(By locator) {
        try {
            return driver.findElement(locator).isEnabled();
        } catch (Exception e) {
            logger.error("Element not enabled {} : {}", locator, e.getMessage());
            return false;
        }
    }

    // Get element count
    public int getElementCount(By locator) {
        try {
            return driver.findElements(locator).size();
        } catch (Exception e) {
            logger.error("Unable to get element count {} : {}", locator, e.getMessage());
            return 0;
        }
    }

    // Select dropdown by visible text
    public void selectByVisibleText(By locator, String text) {
        try {
            Select select = new Select(driver.findElement(locator));
            select.selectByVisibleText(text);
            logger.info("Selected '{}' from {}", text, locator);
        } catch (Exception e) {
            logger.error("Unable to select visible text {} : {}", locator, e.getMessage());
        }
    }

    // Select dropdown by value
    public void selectByValue(By locator, String value) {
        try {
            Select select = new Select(driver.findElement(locator));
            select.selectByValue(value);
            logger.info("Selected value '{}' from {}", value, locator);
        } catch (Exception e) {
            logger.error("Unable to select value {} : {}", locator, e.getMessage());
        }
    }

    // Navigate to URL
    public void navigateToUrl(String url) {
        try {
            driver.get(url);
            logger.info("Navigated to {}", url);
        } catch (Exception e) {
            logger.error("Unable to navigate to {} : {}", url, e.getMessage());
        }
    }

    // Get page title
    public String getPageTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            logger.error("Unable to get page title: {}", e.getMessage());
            return null;
        }
    }

    // Get current URL
    public String getCurrentUrl() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            logger.error("Unable to get current URL: {}", e.getMessage());
            return null;
        }
    }

    // Refresh page
    public void refreshPage() {
        try {
            driver.navigate().refresh();
            logger.info("Page refreshed");
        } catch (Exception e) {
            logger.error("Unable to refresh page: {}", e.getMessage());
        }
    }

    // Hover over element
    public void hoverOverElement(By locator) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(locator)).perform();
            logger.info("Hovered over {}", locator);
        } catch (Exception e) {
            logger.error("Unable to hover over {} : {}", locator, e.getMessage());
        }
    }

    // Wait for page load
    public void waitForPageLoad(int timeOutInSec) {
        try {
            wait.withTimeout(Duration.ofSeconds(timeOutInSec))
                    .until(webDriver -> ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState")
                            .equals("complete"));

            logger.info("Page loaded successfully");

        } catch (Exception e) {
            logger.error("Page did not load within {} seconds", timeOutInSec);
        }
    }

    // Scroll to element
    public void scrollToElement(By locator) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(locator);
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            logger.info("Scrolled to {}", locator);
        } catch (Exception e) {
            logger.error("Unable to scroll to {} : {}", locator, e.getMessage());
        }
    }

    // Press Enter
    public void pressEnter(By locator) {
        try {
            driver.findElement(locator).sendKeys(Keys.ENTER);
            logger.info("Pressed ENTER on {}", locator);
        } catch (Exception e) {
            logger.error("Unable to press ENTER on {} : {}", locator, e.getMessage());
        }
    }

    // Switch to new tab
    public void switchToNewTab() {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        logger.info("Switched to new browser tab");
    }
}