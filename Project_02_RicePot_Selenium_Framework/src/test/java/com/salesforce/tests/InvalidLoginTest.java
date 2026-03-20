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
@Feature("Invalid Login Scenarios")
public class InvalidLoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        DriverFactory.getDriver().navigate().to(ConfigReader.get("url"));
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @Test(
            testName = "TC_003_InvalidUsernameAndPassword",
            description = "Verify error shown for invalid email and invalid password"
    )
    @Story("Both Credentials Invalid")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the login page displays an error message when both username and password are incorrect.")
    public void TC_003_InvalidUsernameAndPassword() {
        try {
            loginPage.doLogin(
                    ConfigReader.get("invalid.username"),
                    ConfigReader.get("invalid.password")
            );
            Assert.assertTrue(
                    loginPage.isErrorDisplayed(),
                    "TC_003 FAILED: Error message should be displayed for invalid username and password."
            );
        } catch (AssertionError ae) {
            throw ae;
        } catch (Exception e) {
            Assert.fail("TC_003 encountered an unexpected exception: " + e.getMessage());
        }
    }

    @Test(
            testName = "TC_004_ValidUsernameInvalidPassword",
            description = "Verify error shown for valid email and wrong password"
    )
    @Story("Wrong Password for Valid User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the login page displays an error message when the username is valid but the password is incorrect.")
    public void TC_004_ValidUsernameInvalidPassword() {
        try {
            loginPage.doLogin(
                    ConfigReader.get("valid.username"),
                    ConfigReader.get("invalid.password")
            );
            Assert.assertTrue(
                    loginPage.isErrorDisplayed(),
                    "TC_004 FAILED: Error message should be displayed for incorrect password."
            );
        } catch (AssertionError ae) {
            throw ae;
        } catch (Exception e) {
            Assert.fail("TC_004 encountered an unexpected exception: " + e.getMessage());
        }
    }

    @Test(
            testName = "TC_005_EmptyUsername",
            description = "Verify error shown when username is blank"
    )
    @Story("Blank Username")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the login page displays an error message when the username field is left empty.")
    public void TC_005_EmptyUsername() {
        try {
            loginPage.doLogin("", ConfigReader.get("valid.password"));
            Assert.assertTrue(
                    loginPage.isErrorDisplayed(),
                    "TC_005 FAILED: Error message should be displayed for blank username."
            );
        } catch (AssertionError ae) {
            throw ae;
        } catch (Exception e) {
            Assert.fail("TC_005 encountered an unexpected exception: " + e.getMessage());
        }
    }

    @Test(
            testName = "TC_006_EmptyPassword",
            description = "Verify error shown when password is blank"
    )
    @Story("Blank Password")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the login page displays an error message when the password field is left empty.")
    public void TC_006_EmptyPassword() {
        try {
            loginPage.doLogin(ConfigReader.get("valid.username"), "");
            Assert.assertTrue(
                    loginPage.isErrorDisplayed(),
                    "TC_006 FAILED: Error message should be displayed for blank password."
            );
        } catch (AssertionError ae) {
            throw ae;
        } catch (Exception e) {
            Assert.fail("TC_006 encountered an unexpected exception: " + e.getMessage());
        }
    }

    @Test(
            testName = "TC_007_EmptyCredentials",
            description = "Verify error shown when both username and password are blank"
    )
    @Story("Both Fields Blank")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the login page displays an error message when both username and password fields are left empty.")
    public void TC_007_EmptyCredentials() {
        try {
            loginPage.doLogin("", "");
            Assert.assertTrue(
                    loginPage.isErrorDisplayed(),
                    "TC_007 FAILED: Error message should be displayed when both credentials are empty."
            );
        } catch (AssertionError ae) {
            throw ae;
        } catch (Exception e) {
            Assert.fail("TC_007 encountered an unexpected exception: " + e.getMessage());
        }
    }

    @Test(
            testName = "TC_008_InvalidEmailFormat",
            description = "Verify error shown for malformed email format in username field"
    )
    @Story("Malformed Email as Username")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify that the login page displays an error message when the username does not follow a valid email format.")
    public void TC_008_InvalidEmailFormat() {
        try {
            loginPage.doLogin("notanemail@@##invalid", ConfigReader.get("valid.password"));
            Assert.assertTrue(
                    loginPage.isErrorDisplayed(),
                    "TC_008 FAILED: Error message should be displayed for a malformed email format."
            );
        } catch (AssertionError ae) {
            throw ae;
        } catch (Exception e) {
            Assert.fail("TC_008 encountered an unexpected exception: " + e.getMessage());
        }
    }
}
