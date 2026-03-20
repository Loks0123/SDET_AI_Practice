package com.salesforce.tests;

import com.salesforce.base.BaseTest;
import com.salesforce.pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class ValidLoginTest extends BaseTest {

    @Test(description = "Verify successful login with valid credentials")
    public void validLoginTest() {
        try {
            driver.get("https://login.salesforce.com/?locale=in");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.enterCredentialsAndSubmit("valid.user@salesforce.com", "validPassword123!");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            boolean isUrlChanged = wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("https://login.salesforce.com/?locale=in")));
            
            Assert.assertTrue(isUrlChanged, "Login failed: URL did not change after valid credentials submission");
            Assert.assertFalse(driver.getCurrentUrl().contains("login"), "Login failed: Still on login-related page");
        } catch (Exception e) {
            Assert.fail("Exception occurred during ValidLoginTest: " + e.getMessage());
        }
    }
}
