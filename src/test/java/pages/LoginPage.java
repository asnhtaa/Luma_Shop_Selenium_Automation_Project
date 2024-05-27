package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractPage {

    private final By emailLocator = By.cssSelector("input#email");
    private final By passwordLocator = By.cssSelector("input#pass");
    private final By signInBtnLocator = By.cssSelector("button.action.login");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Entering user email {value}")
    public void enterEmail(String value) {
        findElement(emailLocator).sendKeys(value);
    }

    @Step("Entering user password {value}")
    public void enterPassword(String value) {
        findElement(passwordLocator).sendKeys(value);
    }

    @Step("Clicking sign in button")
    public void clickSignInBtn() {
        findElement(signInBtnLocator).click();
    }

    @Step("Logging in")
    public void loggingIn(String emailValue, String passwordValue) {
        enterEmail(emailValue);
        enterPassword(passwordValue);
        clickSignInBtn();
    }
}
