package com.orangehrm.pages;

import com.orangehrm.actiondriver.ActionDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.*;

public class DashboardPage {
    private ActionDriver actionDriver;

    // Define locators using By class
    private By adminTab = By.xpath("//span[text()='Admin']");
    private By userIDButton = By.className("oxd-userdropdown-name");
    private By logoutButton = By.xpath("//a[text()='Logout']");
    private By orangeHRMlogo = By.xpath("//div[@class='oxd-brand-banner']//img");
    private By dashboardHeading = By.xpath("//h6[text()='Dashboard']");
    private By timeAtWorkCard = By.xpath("//p[text()='Time at Work']");
    private By myActionsCard = By.xpath("//p[text()='My Actions']");
    private By quickLaunchCard = By.xpath("//p[text()='Quick Launch']");
    private By userDropdownName = By.className("oxd-userdropdown-name");

    /*
     * public DashboardPage(WebDriver driver) { this.actionDriver= new
     * ActionDriver(driver); }
     */

    public DashboardPage(WebDriver driver) {
        this.actionDriver = new ActionDriver(driver);
    }

    // Method to verify if Admin tab is visible
    public boolean isAdminTabVisible() {
        return actionDriver.isElementDisplayed(adminTab);
    }

    public boolean isOrangeHRMLogoDisplayed() {
        return actionDriver.isElementDisplayed(orangeHRMlogo);
    }

    // Method to perform logout operation
    public void logout() {
        actionDriver.click(userIDButton);
        actionDriver.click(logoutButton);
    }

    public boolean isDashboardDisplayed() {
        return actionDriver.isElementDisplayed(dashboardHeading);
    }

    // Verify Time At Work card displayed
    public boolean isTimeAtWorkDisplayed() {
        return actionDriver.isElementDisplayed(timeAtWorkCard);
    }

    // Verify My Actions card displayed
    public boolean isMyActionsDisplayed() {
        return actionDriver.isElementDisplayed(myActionsCard);
    }

    // Verify Quick Launch card displayed
    public boolean isQuickLaunchDisplayed() {
        return actionDriver.isElementDisplayed(quickLaunchCard);
    }

    // Verify user dropdown displayed
    public boolean isUserDropdownDisplayed() {
        return actionDriver.isElementDisplayed(userDropdownName);
    }
}
