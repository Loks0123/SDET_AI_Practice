package com.salesforce.tests;

import com.salesforce.base.BaseTest;
import com.salesforce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseTest {

    @Test(description = "Verify error message on login with invalid credentials")
    public void invalidLoginTest() {
        try {
            driver.get("https://login.salesforce.com/?locale=in");
            LoginPage loginPage = new LoginPage(driver);
            loginPage.enterCredentialsAndSubmit("invaliduser@test.com", "wrongpassword");

            String actualErrorMessage = loginPage.getErrorMessage();
            String expectedErrorTextSnippet = "check your username and password";

            Assert.assertTrue(actualErrorMessage.contains(expectedErrorTextSnippet), 
                "Error message verification failed. Actual message: " + actualErrorMessage);
        } catch (Exception e) {
            Assert.fail("Exception occurred during InvalidLoginTest: " + e.getMessage());
        }
    }
}
