package com.orangehrm.test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageFunctionalTest extends BaseClass {

    private LoginPage loginPage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
    }

    @Test(priority = 1)
    public void verifyLoginPageDisplayedTest() {

        Assert.assertTrue(
                loginPage.isLoginPageDisplayed(),
                "Login page is not displayed."
        );
    }

    @Test(priority = 2)
    public void verifyLoginHeadingDisplayedTest() {

        Assert.assertTrue(
                loginPage.isLoginHeadingDisplayed(),
                "Login heading is not displayed."
        );
    }

    @Test(priority = 3)
    public void verifyLogoDisplayedTest() {

        Assert.assertTrue(
                loginPage.isLogoDisplayed(),
                "OrangeHRM logo is not displayed."
        );
    }

    @Test(priority = 4)
    public void verifyUsernameFieldDisplayedTest() {

        Assert.assertTrue(
                loginPage.isUsernameFieldDisplayed(),
                "Username field is not displayed."
        );
    }

    @Test(priority = 5)
    public void verifyPasswordFieldDisplayedTest() {

        Assert.assertTrue(
                loginPage.isPasswordFieldDisplayed(),
                "Password field is not displayed."
        );
    }

    @Test(priority = 6)
    public void verifyLoginButtonDisplayedTest() {

        Assert.assertTrue(
                loginPage.isLoginButtonDisplayed(),
                "Login button is not displayed."
        );
    }

    @Test(priority = 7)
    public void verifyForgotPasswordLinkDisplayedTest() {

        Assert.assertTrue(
                loginPage.isForgotPasswordLinkDisplayed(),
                "Forgot Password link is not displayed."
        );
    }
}