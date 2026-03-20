package com.salesforce.tests;

import com.salesforce.base.BaseTest;
import com.salesforce.pages.LoginPage;
import com.salesforce.utils.ConfigReader;
import com.salesforce.utils.DriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Salesforce Login Page Automation")
@Feature("Valid Login Scenarios")
public class ValidLoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @Test(
            testName = "TC_001_ValidLogin",
            description = "Verify successful login with valid credentials"
    )
    @Story("Valid Credentials Login")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that a user with valid email and password is redirected away from the login page to the Salesforce home page.")
    public void TC_001_ValidLogin() {
        try {
            loginPage.doLogin(
                    ConfigReader.get("valid.username"),
                    ConfigReader.get("valid.password")
            );
            Assert.assertFalse(
                    loginPage.getCurrentUrl().contains("login.salesforce.com"),
                    "TC_001 FAILED: User should be redirected away from the login page after valid login."
            );
        } catch (AssertionError ae) {
            throw ae;
        } catch (Exception e) {
            Assert.fail("TC_001 encountered an unexpected exception: " + e.getMessage());
        }
    }

    @Test(
            testName = "TC_002_ValidLoginWithRememberMe",
            description = "Verify successful login with Remember Me checked"
    )
    @Story("Valid Login with Remember Me")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a user can login successfully with the Remember Me checkbox enabled, persisting session identity.")
    public void TC_002_ValidLoginWithRememberMe() {
        try {
            loginPage.checkRememberMe();
            loginPage.doLogin(
                    ConfigReader.get("valid.username"),
                    ConfigReader.get("valid.password")
            );
            Assert.assertFalse(
                    loginPage.getCurrentUrl().contains("login.salesforce.com"),
                    "TC_002 FAILED: User should be redirected away from the login page with Remember Me enabled."
            );
        } catch (AssertionError ae) {
            throw ae;
        } catch (Exception e) {
            Assert.fail("TC_002 encountered an unexpected exception: " + e.getMessage());
        }
    }
}
