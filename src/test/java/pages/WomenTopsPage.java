package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WomenTopsPage extends AbstractPage {

    private final By colorFilterLocator = By.xpath("//div[@class='filter-options-title' and contains(text(), 'Color')]");
    private final By selectedColorLocator = By.cssSelector("div[aria-label='Color'] div[option-label='Blue']");
    private final By priceFilterLocator = By.xpath("//div[@class='filter-options-title' and contains(text(), 'Price')]");
    private final By clearFilterLocator = By.cssSelector("a.action.filter-clear");
    private final By filteredItemsPriceLocator = By.cssSelector("span.price-wrapper");

    public WomenTopsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Filter items by color")
    public void filterItemsByColor(String color) {
        findElement(colorFilterLocator).click();
        findElement(By.cssSelector("div.filter-options div[option-label='" + color + "']")).click();
    }


    @Step("Checking that each item is present in selected color")
    public void checkEachItemIsInSelectedColor() {
        List<WebElement> selectedColor = driver.findElements(selectedColorLocator);
        selectedColor.forEach(element ->
                assertTrue(element.getAttribute("class").contains("selected"),
                        "Filtration by color was applied incorrectly!"));
    }

    @Step("Filter items by price")
    public void filterItemsByPrice(String priceFilterValue) {
        findElement(priceFilterLocator).click();
        findElement(By.xpath("//div[@class='filter-options-content']//span[text()='" + priceFilterValue + "']")).click();
    }

    @Step("Checking that prices are in selected price range")
    public void checkPricesInRange(int minPrice, int maxPrice) {
        List<WebElement> productPrices = driver.findElements(filteredItemsPriceLocator);
        productPrices.forEach(priceElement -> {
            int priceValue = Integer.parseInt(priceElement.getAttribute("data-price-amount"));
            assertTrue(priceValue >= minPrice && priceValue <= maxPrice,
                    "Price is not in selected range");
        });
    }

    @Step("Clicking 'Clear all' filters")
    public void clickClearAll() {
        findElement(clearFilterLocator).click();
    }

    @Step("Checking that prices are not in selected price range")
    public void checkPricesNotInRange(int minPrice, int maxPrice) {
        List<WebElement> productPrices = driver.findElements(filteredItemsPriceLocator);
        productPrices.forEach(priceElement -> {
            int priceValue = Integer.parseInt(priceElement.getAttribute("data-price-amount"));
            assertFalse(priceValue >= minPrice && priceValue <= maxPrice,
                    "Filter is not cleared");
        });
    }
}
