package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractPage {

    private final By emailLocator = By.cssSelector("input#email");
    private final By passwordLocator = By.cssSelector("[title='Password']");
    private final By signInBtnLocator = By.cssSelector("button.login");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Entering user email {value}")
    public void enterEmail(String value) {
        fillElementWithValue(emailLocator, value);
    }

    @Step("Entering user password {value}")
    public void enterPassword(String value) {
        fillElementWithValue(passwordLocator, value);
    }

    @Step("Clicking sign in button")
    public void clickSignInBtn() {
        clickElement(signInBtnLocator);
    }

    @Step("Logging in")
    public void loggingIn(String emailValue, String passwordValue) {
        enterEmail(emailValue);
        enterPassword(passwordValue);
        clickSignInBtn();
    }
}
