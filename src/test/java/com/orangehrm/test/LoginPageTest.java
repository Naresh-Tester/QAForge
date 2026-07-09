    package com.orangehrm.test;

    import com.orangehrm.base.BaseClass;
    import com.orangehrm.pages.DashboardPage;
    import com.orangehrm.pages.LoginPage;
    import org.testng.Assert;
    import org.testng.annotations.BeforeMethod;
    import org.testng.annotations.Test;
    import com.orangehrm.utilities.ConfigReader;
    import io.qameta.allure.*;

    @Epic("OrangeHRM Login Module")
    @Feature("Login Page")
    public class LoginPageTest extends BaseClass {

        private LoginPage loginPage;
        private DashboardPage dashboardPage;

        @BeforeMethod
        public void setupPages() {
            loginPage = new LoginPage(getDriver());
            dashboardPage = new DashboardPage(getDriver());
        }


        // ==========================================
        // UI TESTS
        // ==========================================


        @Test(groups = {"smoke", "regression", "functional"})
        @Severity(SeverityLevel.CRITICAL)
        @Story("Verify Login Page")
        @Description("Verify login page is displayed successfully")
        public void verifyLoginPageDisplayedTest() {
            Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed.");
        }

        @Test(groups = {"smoke", "regression", "functional"})
        @Severity(SeverityLevel.NORMAL)
        @Story("Verify Login Page Heading")
        @Description("Verify Login heading is displayed")
        public void verifyLoginHeadingDisplayedTest() {
            Assert.assertTrue(loginPage.isLoginHeadingDisplayed(), "Login heading is not displayed.");
        }

        @Test(groups = {"smoke", "regression", "functional"})
        @Severity(SeverityLevel.NORMAL)
        @Story("Verify Company Logo")
        @Description("Verify OrangeHRM logo is displayed")
        public void verifyLogoDisplayedTest() {
            Assert.assertTrue(loginPage.isLogoDisplayed(), "OrangeHRM logo is not displayed.");
        }

        @Test(groups = {"smoke", "regression", "functional"})
        @Severity(SeverityLevel.NORMAL)
        @Story("Verify Username Field")
        @Description("Verify Username field is displayed")
        public void verifyUsernameFieldDisplayedTest() {
            Assert.assertTrue(loginPage.isUsernameFieldDisplayed(), "Username field is not displayed.");
        }

        @Test(groups = {"smoke", "regression", "functional"})
        @Severity(SeverityLevel.NORMAL)
        @Story("Verify Password Field")
        @Description("Verify Password field is displayed")
        public void verifyPasswordFieldDisplayedTest() {
            Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field is not displayed.");
        }

        @Test(groups = {"smoke", "regression", "functional"})
        @Severity(SeverityLevel.NORMAL)
        @Story("Verify Login Button")
        @Description("Verify Login button is displayed")
        public void verifyLoginButtonDisplayedTest() {
            Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button is not displayed.");
        }

        @Test(groups = {"sanity", "regression", "functional"})
        @Severity(SeverityLevel.NORMAL)
        @Story("Verify Forgot Password Link")
        @Description("Verify Forgot Password link is displayed")
        public void verifyForgotPasswordLinkDisplayedTest() {
            Assert.assertTrue(loginPage.isForgotPasswordLinkDisplayed(), "Forgot Password link is not displayed.");
        }

        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.NORMAL)
        @Story("Verify LinkedIn Icon")
        @Description("Verify LinkedIn icon is displayed")
        public void verifyLinkedInIconDisplayedTest() {
            Assert.assertTrue(loginPage.isLinkedInIconDisplayed(), "LinkedIn icon is not displayed.");
        }

        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.NORMAL)
        @Story("Verify Facebook Icon")
        @Description("Verify Facebook icon is displayed")
        public void verifyFacebookIconDisplayedTest() {
            Assert.assertTrue(loginPage.isFacebookIconDisplayed(), "Facebook icon is not displayed.");
        }

        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.NORMAL)
        @Story("Verify Twitter Icon")
        @Description("Verify Twitter icon is displayed")
        public void verifyTwitterIconDisplayedTest() {
            Assert.assertTrue(loginPage.isTwitterIconDisplayed(), "Twitter icon is not displayed.");
        }

        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.NORMAL)
        @Story("Verify YouTube Icon")
        @Description("Verify YouTube icon is displayed")
        public void verifyYoutubeIconDisplayedTest() {
            Assert.assertTrue(loginPage.isYoutubeIconDisplayed(), "YouTube icon is not displayed.");
        }


        // ==========================================
        // FUNCTIONAL TESTS
        // ==========================================


        @Test(groups = {"smoke", "sanity", "regression", "functional"})
        @Severity(SeverityLevel.BLOCKER)
        @Story("Verify Valid Login")
        @Description("Verify user can login with valid credentials")
        public void verifyValidLoginTest() {
            loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
            Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "User is not navigated to Dashboard after successful login.");
        }

        @Test(groups = {"sanity", "regression", "functional"})
        @Severity(SeverityLevel.CRITICAL)
        @Story("Verify Forgot Password Navigation")
        @Description("Verify user can navigate to Forgot Password page")
        public void verifyForgotPasswordNavigationTest() {
            loginPage.clickForgotPassword();
            Assert.assertTrue(loginPage.isResetPasswordPageDisplayed(), "Reset Password page is not displayed.");
        }

        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.MINOR)
        @Story("Verify LinkedIn Link")
        @Description("Verify LinkedIn link is clickable")
        public void verifyLinkedInLinkClickableTest() {
            loginPage.clickLinkedInIcon();
            loginPage.switchToNewTab();
            Assert.assertTrue(driver.getCurrentUrl().contains("linkedin"));
        }

        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.MINOR)
        @Story("Verify Facebook Link")
        @Description("Verify Facebook link is clickable")
        public void verifyFacebookNavigationTest() {
            loginPage.clickFacebookIcon();
            loginPage.switchToNewTab();
            Assert.assertTrue(driver.getCurrentUrl().contains("facebook"));
        }

        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.MINOR)
        @Story("Verify Twitter Link")
        @Description("Verify Twitter link is clickable")
        public void verifyTwitterNavigationTest() {
            loginPage.clickTwitterIcon();
            loginPage.switchToNewTab();
            Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("twitter") || driver.getCurrentUrl().toLowerCase().contains("x.com"), "Twitter page was not opened.");
        }

        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.MINOR)
        @Story("Verify YouTube Link")
        @Description("Verify YouTube link is clickable")
        public void verifyYoutubeNavigationTest() {
            loginPage.clickYoutubeIcon();
            loginPage.switchToNewTab();
            Assert.assertTrue(driver.getCurrentUrl().contains("youtube"));
        }


        // ==========================================
        // VALIDATION TESTS
        // ==========================================


        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.NORMAL)
        @Story("Verify Empty Username")
        @Description("Verify error message is displayed for empty username")
        public void verifyEmptyUsernameTest() {
            loginPage.login("", ConfigReader.getPassword());
            Assert.assertEquals(loginPage.getUsernameRequiredMessage(), "Required");
        }

        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.NORMAL)
        @Story("Verify Empty Password")
        @Description("Verify error message is displayed for empty password")
        public void verifyEmptyPasswordTest() {
            loginPage.login(ConfigReader.getUsername(), "");
            Assert.assertEquals(loginPage.getPasswordRequiredMessage(), "Required");
        }

        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.NORMAL)
        @Story("Verify Empty Username and Password")
        @Description("Verify error messages are displayed for empty username and password")
        public void verifyEmptyUsernameAndPasswordTest() {
            loginPage.login("", "");
            Assert.assertEquals(loginPage.getUsernameRequiredMessage(), "Required");
            Assert.assertEquals(loginPage.getPasswordRequiredMessage(), "Required");
        }
        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.NORMAL)
        @Story("Verify Username Border Color")
        @Description("Verify username border turns red for empty username")
        public void verifyUsernameBorderTurnsRedTest() {
            loginPage.login("", ConfigReader.getPassword());
            Assert.assertTrue(loginPage.getUsernameBorderColor().contains("235"), "Username border is not red."
            );
        }

        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.NORMAL)
        @Story("Verify Password Border Color")
        @Description("Verify password border turns red for empty password")
        public void verifyPasswordBorderTurnsRedTest() {
            loginPage.login(ConfigReader.getUsername(), "");
            Assert.assertTrue(loginPage.getPasswordBorderColor().contains("235"), "Password border is not red.");
        }


        // ==========================================
        // NEGATIVE TESTS
        // ==========================================


        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.CRITICAL)
        @Story("Verify Invalid Username")
        @Description("Verify error message is displayed for invalid username")
        public void verifyInvalidUsernameTest() {
            loginPage.login("InvalidUser", ConfigReader.getPassword());
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Invalid credentials message is not displayed.");
        }

        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.CRITICAL)
        @Story("Verify Invalid Password")
        @Description("Verify error message is displayed for invalid password")
        public void verifyInvalidPasswordTest() {
            loginPage.login(ConfigReader.getUsername(), "InvalidPassword");
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Invalid credentials message is not displayed.");
        }

        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.CRITICAL)
        @Story("Verify Invalid Username and Password")
        @Description("Verify error message is displayed for invalid username and password")
        public void verifyInvalidUsernameAndPasswordTest() {
            loginPage.login("InvalidUser", "InvalidPassword");
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Invalid credentials message is not displayed.");
        }

        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.CRITICAL)
        @Story("Verify Invalid Credentials Message")
        @Description("Verify invalid credentials message is displayed")
        public void verifyInvalidCredentialMessageTest() {
            loginPage.login("InvalidUser", "InvalidPassword");
            Assert.assertEquals(loginPage.getInvalidCredentialMessage(), "Invalid credentials");
        }


        // ==========================================
        // SECURITY TESTS
        // ==========================================


        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.CRITICAL)
        @Story("Verify Password Masked")
        @Description("Verify password field is masked")
        public void verifyPasswordMaskedTest() {
            Assert.assertTrue(loginPage.isPasswordMasked(), "Password field is not masked.");
        }

        @Test(groups = {"regression", "functional"})
        @Severity(SeverityLevel.CRITICAL)
        @Story("Verify Dashboard Access Without Login")
        @Description("Verify user cannot access Dashboard without login")
        public void verifyDashboardAccessWithoutLoginTest() {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
            Assert.assertTrue(loginPage.isLoginPageDisplayed(), "User was able to access Dashboard without login");
        }
    }