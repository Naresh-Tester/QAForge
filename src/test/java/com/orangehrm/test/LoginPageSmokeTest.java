package com.orangehrm.test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageSmokeTest extends BaseClass {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void setupPages() {
        WebDriver driver = getDriver();

        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1, description = "Verify login page is displayed")
    public void verifyLoginPageLoadedTest() {

        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Login page is not displayed.");

    }

    @Test(priority = 2, description = "Verify login with valid credentials")
    public void verifyValidLoginTest() {

        loginPage.login("Admin", "admin123");

        Assert.assertTrue(homePage.isAdminTabVisible(),
                "Admin tab is not visible after successful login.");

        homePage.logout();
    }

    @Test(priority = 3, description = "Verify validation message for empty username and password")
    public void verifyEmptyCredentialsValidationTest() {

        loginPage.login("", "");

        Assert.assertEquals(loginPage.getUsernameRequiredMessage(),
                "Required");

        Assert.assertEquals(loginPage.getPasswordRequiredMessage(),
                "Required");
    }

    @Test(priority = 4, description = "Verify login with invalid username")
    public void verifyInvalidUsernameTest() {

        loginPage.login("InvalidUser", "admin123");

        Assert.assertEquals(loginPage.getInvalidCredentialMessage(),
                "Invalid credentials");
    }

    @Test(priority = 5, description = "Verify login with invalid password")
    public void verifyInvalidPasswordTest() {

        loginPage.login("Admin", "wrongPassword");

        Assert.assertEquals(loginPage.getInvalidCredentialMessage(),
                "Invalid credentials");
    }

    @Test(priority = 6, description = "Verify logout functionality")
    public void verifyLogoutTest() {

        loginPage.login("Admin", "admin123");

        Assert.assertTrue(homePage.isAdminTabVisible());

        homePage.logout();

        Assert.assertTrue(loginPage.isLoginPageDisplayed(),
                "Login page is not displayed after logout.");
    }

}