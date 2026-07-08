package com.orangehrm.pages;

import com.orangehrm.actiondriver.ActionDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class LoginPage {

    private ActionDriver actionDriver;

    //Define locators using By class
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//p[text()='Invalid credentials']");


    public LoginPage(WebDriver driver) {
        this.actionDriver = new ActionDriver(driver);
    }

    //Method to perform login
    public void login(String userName, String password) {
        actionDriver.type(usernameField, userName);
        actionDriver.type(passwordField, password);
        actionDriver.click(loginButton);
    }

    //Method to check if error message is displayed
    public boolean isErrorMessageDisplayed() {
        return actionDriver.isElementDisplayed(errorMessage);
    }

    //Method to get the text from Error message
    public String getErrorMessageText() {
        return actionDriver.getText(errorMessage);
    }

    //Verify if error is correct or not
    public boolean verifyErrorMessage(String expectedError) {
        return actionDriver.compareElementText(errorMessage, expectedError);
    }
}
