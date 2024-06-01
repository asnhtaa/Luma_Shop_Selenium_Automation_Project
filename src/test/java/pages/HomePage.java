package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends AbstractPage {

    private final By signInBtnLocator = By.cssSelector("header .authorization-link");
    private final By topsLinkLocator = By.cssSelector("li.level1.nav-2-1");
    private final By womenLinkLocator = By.cssSelector("nav a[href$='/women.html']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Going to Registration Page")
    public void openSignInPage() {
        clickElement(signInBtnLocator);
    }

    @Step("Going to Women Tops Page")
    public void clickWomenTops() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(womenLinkLocator)).perform();
        clickElement(topsLinkLocator);
    }
}

