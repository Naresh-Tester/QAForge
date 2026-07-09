package com.orangehrm.test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utilities.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.qameta.allure.*;

@Epic("OrangeHRM Dashboard Module")
@Feature("Dashboard Page")
public class DashboardPageTest extends BaseClass {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;


    @BeforeMethod
    public void setupPages() {

        loginPage = new LoginPage(getDriver());
        dashboardPage = new DashboardPage(getDriver());

        loginPage.login(
                ConfigReader.getUsername(),
                ConfigReader.getPassword()
        );
    }


    // ==========================================
    // UI TESTS
    // ==========================================


    @Test(groups = {"smoke","regression","functional"})
    @Severity(SeverityLevel.BLOCKER)
    @Story("Verify Dashboard Display")
    @Description("Verify dashboard page is displayed after successful login")
    public void verifyDashboardDisplayedTest() {
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard is not displayed.");
    }

    @Test(groups = {"regression","functional"})
    @Severity(SeverityLevel.CRITICAL)
    @Story("Verify Admin Menu")
    @Description("Verify Admin tab is displayed on dashboard")
    public void verifyAdminTabDisplayedTest() {
        Assert.assertTrue(dashboardPage.isAdminTabVisible(), "Admin tab is not displayed.");
    }

    @Test(groups = {"regression","functional"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Verify Dashboard Logo")
    @Description("Verify OrangeHRM logo is displayed on dashboard")
    public void verifyOrangeHRMLogoDisplayedTest() {
        Assert.assertTrue(dashboardPage.isOrangeHRMLogoDisplayed(), "OrangeHRM logo is not displayed.");
    }

    @Test(groups = {"regression","functional"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Verify User Dropdown")
    @Description("Verify user dropdown menu is displayed on dashboard")
    public void verifyUserDropdownDisplayedTest() {
        Assert.assertTrue(dashboardPage.isUserDropdownDisplayed(), "User dropdown is not displayed.");
    }

    @Test(groups = {"regression","functional"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Verify Time At Work Card")
    @Description("Verify Time At Work card is displayed on dashboard")
    public void verifyTimeAtWorkDisplayedTest() {
        Assert.assertTrue(dashboardPage.isTimeAtWorkDisplayed(), "Time At Work card is not displayed.");
    }

    @Test(groups = {"regression","functional"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Verify My Actions Card")
    @Description("Verify My Actions card is displayed on dashboard")
    public void verifyMyActionsDisplayedTest() {
        Assert.assertTrue(dashboardPage.isMyActionsDisplayed(), "My Actions card is not displayed.");
    }

    @Test(groups = {"regression","functional"})
    @Severity(SeverityLevel.NORMAL)
    @Story("Verify Quick Launch Card")
    @Description("Verify Quick Launch card is displayed on dashboard")
    public void verifyQuickLaunchDisplayedTest() {
        Assert.assertTrue(dashboardPage.isQuickLaunchDisplayed(), "Quick Launch card is not displayed.");
    }


    // ==========================================
    // FUNCTIONAL TESTS
    // ==========================================


    @Test(groups = {"sanity","regression","functional"})
    @Severity(SeverityLevel.BLOCKER)
    @Story("User Logout")
    @Description("Verify user can logout successfully and navigate back to login page")
    public void verifyLogoutTest() {
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "User is not redirected to login page after logout.");
    }

}