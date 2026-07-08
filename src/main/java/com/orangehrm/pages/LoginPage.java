package com.orangehrm.pages;

import com.orangehrm.actiondriver.ActionDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private ActionDriver actionDriver;

    // Locators
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By loginHeading = By.xpath("//h5[text()='Login']");
    private By usernameRequiredMessage = By.xpath("(//span[text()='Required'])[1]");
    private By passwordRequiredMessage = By.xpath("(//span[text()='Required'])[2]");
    private By errorMessage = By.xpath("//p[text()='Invalid credentials']");
    private By orangeHrmLogo = By.xpath("//img[@alt='company-branding']");
    private By forgotPasswordLink = By.cssSelector(".orangehrm-login-forgot-header");

    public LoginPage(WebDriver driver) {
        this.actionDriver = new ActionDriver(driver);
    }

    // Login
    public void login(String userName, String password) {
        actionDriver.type(usernameField, userName);
        actionDriver.type(passwordField, password);
        actionDriver.click(loginButton);
    }

    // Verify Login page displayed
    public boolean isLoginPageDisplayed() {
        return actionDriver.isElementDisplayed(loginHeading);
    }

    // Username required validation
    public String getUsernameRequiredMessage() {
        return actionDriver.getText(usernameRequiredMessage);
    }

    // Password required validation
    public String getPasswordRequiredMessage() {
        return actionDriver.getText(passwordRequiredMessage);
    }

    // Invalid credentials displayed
    public boolean isErrorMessageDisplayed() {
        return actionDriver.isElementDisplayed(errorMessage);
    }

    // Get invalid credentials text
    public String getInvalidCredentialMessage() {
        return actionDriver.getText(errorMessage);
    }

    // Verify invalid credentials message
    public boolean verifyErrorMessage(String expectedError) {
        return actionDriver.compareElementText(errorMessage, expectedError);
    }

    public boolean isLoginHeadingDisplayed() {
        return actionDriver.isElementDisplayed(loginHeading);
    }

    public boolean isLogoDisplayed() {
        return actionDriver.isElementDisplayed(orangeHrmLogo);
    }

    public boolean isUsernameFieldDisplayed() {
        return actionDriver.isElementDisplayed(usernameField);
    }

    public boolean isPasswordFieldDisplayed() {
        return actionDriver.isElementDisplayed(passwordField);
    }

    public boolean isLoginButtonDisplayed() {
        return actionDriver.isElementDisplayed(loginButton);
    }

    public boolean isForgotPasswordLinkDisplayed() {
        return actionDriver.isElementDisplayed(forgotPasswordLink);
    }
}