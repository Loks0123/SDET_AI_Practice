package com.salesforce.pages;

import com.salesforce.utils.DriverFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {

    private final WebDriver driver;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//div[@id='error']")
    private WebElement errorMessageContainer;

    @FindBy(xpath = "//div[@id='error']//h3")
    private WebElement errorMessageHeading;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Enter username: {username}")
    public void enterUsername(String username) {
        DriverFactory.getWait().until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    @Step("Enter password")
    public void enterPassword(String password) {
        DriverFactory.getWait().until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @Step("Click the Login button")
    public void clickLoginButton() {
        DriverFactory.getWait().until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    @Step("Check the Remember Me checkbox")
    public void checkRememberMe() {
        DriverFactory.getWait().until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
        if (!rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
        }
    }

    @Step("Perform login with username: {username}")
    public void doLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Get error message text from the login page")
    public String getErrorMessage() {
        DriverFactory.getWait().until(ExpectedConditions.visibilityOf(errorMessageContainer));
        return errorMessageHeading.getText().trim();
    }

    @Step("Check if error message is visible on the login page")
    public boolean isErrorDisplayed() {
        try {
            DriverFactory.getWait().until(ExpectedConditions.visibilityOf(errorMessageContainer));
            return errorMessageContainer.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Verify that the login page title is present")
    public boolean isLoginPageTitle() {
        return DriverFactory.getWait()
                .until(ExpectedConditions.titleContains("Login"));
    }

    @Step("Get current page URL")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
