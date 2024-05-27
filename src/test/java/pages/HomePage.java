package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends AbstractPage {

    private final By signInBtnLocator = By.linkText("Sign In");
    private final By topsLinkLocator = By.linkText("Tops");
    private final By womenLinkLocator = By.xpath("//nav[@class='navigation']//span[contains(text(), 'Women')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Going to Registration Page")
    public void openSignInPage() {
        driver.findElement(signInBtnLocator).click();
    }

    @Step("Going to Women Tops Page")
    public void clickWomenTops() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(womenLinkLocator)).perform();
        driver.findElement(topsLinkLocator).click();
    }
}

