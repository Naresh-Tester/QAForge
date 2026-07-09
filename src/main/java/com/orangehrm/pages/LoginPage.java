package com.orangehrm.pages;

import com.orangehrm.actiondriver.ActionDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private ActionDriver actionDriver;

    // Locators
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By loginHeading = By.xpath("//h5[text()='Login']");
    private By usernameRequiredMessage = By.xpath("//input[@name='username']/ancestor::div[contains(@class,'oxd-input-group')]//span[text()='Required']");
    private By passwordRequiredMessage = By.xpath("//input[@name='password']/ancestor::div[contains(@class,'oxd-input-group')]//span[text()='Required']");
    private By errorMessage = By.xpath("//p[text()='Invalid credentials']");
    private By orangeHrmLogo = By.xpath("//img[@alt='company-branding']");
    private By forgotPasswordLink = By.cssSelector(".orangehrm-login-forgot-header");
    private By linkedInIcon = By.xpath("//a[contains(@href,'linkedin')]");
    private By facebookIcon = By.xpath("//a[contains(@href,'facebook')]");
    private By twitterIcon = By.xpath("//a[contains(@href,'twitter') or contains(@href,'x.com')]");
    private By youtubeIcon = By.xpath("//a[contains(@href,'youtube')]");
    private By resetPasswordHeading = By.xpath("//h6[text()='Reset Password']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.actionDriver = new ActionDriver(driver);
    }

    // Login
    public void login(String username, String password) {
        actionDriver.type(usernameField, username);
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

    public boolean isPasswordMasked() {
        return driver.findElement(passwordField).getAttribute("type").equals("password");
    }

    public void clickForgotPassword() {
        actionDriver.click(forgotPasswordLink);
    }

    public boolean isResetPasswordPageDisplayed() {
        return actionDriver.isElementDisplayed(resetPasswordHeading);
    }

    public boolean isLinkedInIconDisplayed() {
        return actionDriver.isElementDisplayed(linkedInIcon);
    }

    public boolean isFacebookIconDisplayed() {
        return actionDriver.isElementDisplayed(facebookIcon);
    }

    public boolean isTwitterIconDisplayed() {
        return actionDriver.isElementDisplayed(twitterIcon);
    }

    public boolean isYoutubeIconDisplayed() {
        return actionDriver.isElementDisplayed(youtubeIcon);
    }

    public void clickLinkedInIcon() {
        actionDriver.click(linkedInIcon);
    }

    public void clickFacebookIcon() {
        actionDriver.click(facebookIcon);
    }

    public void clickTwitterIcon() {
        actionDriver.click(twitterIcon);
    }

    public void clickYoutubeIcon() {
        actionDriver.click(youtubeIcon);
    }

    public String getUsernameBorderColor() {
        return driver.findElement(usernameField).getCssValue("border-color");
    }

    public String getPasswordBorderColor() {
        return driver.findElement(passwordField).getCssValue("border-color");
    }

    public void switchToNewTab() {
        actionDriver.switchToNewTab();
    }
}