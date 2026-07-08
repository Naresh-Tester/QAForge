package com.orangehrm.actiondriver;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.orangehrm.base.BaseClass;

import java.time.Duration;

public class ActionDriver {
    private WebDriver driver;
    private WebDriverWait wait;

    public ActionDriver(WebDriver driver) {
        this.driver = driver;
        int explicitWait = Integer.parseInt(BaseClass.getProp().getProperty("explicitWait"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));
        System.out.println("WebDriver instance is created.");
         }

         // Wait for element to be clickable
    public void waitForElementToBeClickable(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            System.out.println("Element is not clickable:" + e.getMessage());
        }
    }

    //Wait for element to be visible
    private void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Compare element text with expected text
    public boolean compareElementText(By locator, String expectedText) {
        try {
            String actualText = getText(locator);
            if (actualText.equals(expectedText.trim())) {
                System.out.println("Text matched: " + actualText);
                return true;
            } else {
                System.out.println("Text mismatch. Expected: " + expectedText + " but got: " + actualText);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Unable to compare element text: " + e.getMessage());
            return false;
        }
    }

    // Wait for element to be invisible
    public void waitForElementToBeInvisible(By locator) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            System.out.println("Element is still visible:" + e.getMessage());
        }
    }

    // Click on element
    public void click(By locator) {
        try {
            waitForElementToBeClickable(locator);
            driver.findElement(locator).click();
        } catch (Exception e) {
            System.out.println("Unable to click element:" + e.getMessage());
        }
    }

    // Type text into element
    public void type(By locator, String text) {
        try {
            waitForElementToBeVisible(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        } catch (Exception e) {
            System.out.println("Unable to type into element:" + e.getMessage());
        }
    }

    // Get text from input field
    public String getText(By locator) {
        try {
            waitForElementToBeVisible(locator);
            return driver.findElement(locator).getText();
        } catch (Exception e) {
            System.out.println("Unable to get text:" + e.getMessage());
            return null;
        }
    }

    // Check if element is displayed
    public boolean isElementDisplayed(By locator) {
        try {
            waitForElementToBeVisible(locator);
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            System.out.println("Element is not displayed: " + e.getMessage());
            return false;
        }
    }

    // Check if element is enabled
    public boolean isElementEnabled(By locator) {
        try {
            return driver.findElement(locator).isEnabled();
        } catch (Exception e) {
            System.out.println("Element is not enabled:" + e.getMessage());
            return false;
        }
    }

    // Get count of matching elements
    public int getElementCount(By locator) {
        try {
            return driver.findElements(locator).size();
        } catch (Exception e) {
            System.out.println("Unable to get element count:" + e.getMessage());
            return 0;
        }
    }

    // Select dropdown option by visible text
    public void selectByVisibleText(By locator, String text) {
        try {
            Select select = new Select(driver.findElement(locator));
            select.selectByVisibleText(text);
        } catch (Exception e) {
            System.out.println("Unable to select by visible text:" + e.getMessage());
        }
    }

    // Select dropdown option by value
    public void selectByValue(By locator, String value) {
        try {
            Select select = new Select(driver.findElement(locator));
            select.selectByValue(value);
        } catch (Exception e) {
            System.out.println("Unable to select by value:" + e.getMessage());
        }
    }

    // Navigate to URL
    public void navigateToUrl(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            System.out.println("Unable to navigate to URL:" + e.getMessage());
        }
    }

    // Get page title
    public String getPageTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            System.out.println("Unable to get page title:" + e.getMessage());
            return null;
        }
    }

    // Get current URL
    public String getCurrentUrl() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            System.out.println("Unable to get current URL:" + e.getMessage());
            return null;
        }
    }

    // Refresh page
    public void refreshPage() {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            System.out.println("Unable to refresh page:" + e.getMessage());
        }
    }

    // Hover over element
    public void hoverOverElement(By locator) {
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(locator)).perform();
        } catch (Exception e) {
            System.out.println("Unable to hover over element:" + e.getMessage());
        }
    }

    // Wait for the page to load
    public void waitForPageLoad(int timeOutInSec) {
        try {
            wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver -> ((JavascriptExecutor) WebDriver)
                    .executeScript("return document.readyState").equals("complete"));
            System.out.println("Page loaded successfully.");
        } catch (Exception e) {
            System.out.println("Page did not load within " + timeOutInSec + " seconds. Exception: " + e.getMessage());
        }
    }

    // Scroll to an element -- Added a semicolon ; at the end of the script string
    public void scrollToElement(By by) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(by);
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            System.out.println("Unable to locate element:" + e.getMessage());
        }
    }

    // Press Enter key on element
    public void pressEnter(By locator) {
        try {
            driver.findElement(locator).sendKeys(Keys.ENTER);
        } catch (Exception e) {
            System.out.println("Unable to press enter:" + e.getMessage());
        }
    }

    }
