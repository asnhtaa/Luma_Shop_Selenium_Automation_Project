package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

public class WomenTopsPage extends AbstractPage {

    private final By colorFilterLocator = By.xpath("//div[@class='filter-options-title' and contains(text(), 'Color')]");
    private final By selectedColorLocator = By.cssSelector("[aria-label='Color'] [option-label='Blue']");
    private final By priceFilterLocator = By.xpath("//div[@class='filter-options-title' and contains(text(), 'Price')]");
    private final By clearFilterLocator = By.cssSelector(".filter-clear");
    private final By filteredItemsPriceLocator = By.cssSelector(".price-wrapper");

    public WomenTopsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Filter items by color")
    public void filterItemsByColor(String color) {
        clickElement(colorFilterLocator);
        clickElement(getColorFilterOptionLocator(color));
    }

    private By getColorFilterOptionLocator(String color) {
        return By.cssSelector(String.format("div.filter-options div[option-label='%s']", color));
    }

    @Step("Checking that each item is present in selected color")
    public void assertEachItemIsInSelectedColor() {
        List<WebElement> selectedColor = driver.findElements(selectedColorLocator);
        selectedColor.forEach(element -> assertThat(element.getAttribute("class"))
                .as("Check if element class contains 'selected'. Actual value: %s", element.getAttribute("class"))
                .contains("selected"));
    }

    @Step("Filter items by price")
    public void filterItemsByPrice(String priceFilterValue) {
        clickElement(priceFilterLocator);
        clickElement(getPriceFilterOptionLocator(priceFilterValue));
    }

    private By getPriceFilterOptionLocator(String priceFilterValue) {
        return By.xpath(String.format("//div[@class='filter-options-content']//span[text()='%s']", priceFilterValue));
    }

    @Step("Checking that prices are in selected price range")
    public void assertPricesAreInSelectedRange(int minPrice, int maxPrice) {
        List<WebElement> productPrices = driver.findElements(filteredItemsPriceLocator);
        productPrices.forEach(priceElement -> {
            int priceValue = Integer.parseInt(priceElement.getAttribute("data-price-amount"));
            assertThat(priceValue)
                    .as("Check if price %d is in the selected range [%d, %d]", priceValue, minPrice, maxPrice)
                    .isGreaterThanOrEqualTo(minPrice)
                    .isLessThanOrEqualTo(maxPrice);
        });
    }

    @Step("Clicking 'Clear all' filters")
    public void clickClearAll() {
        clickElement(clearFilterLocator);
    }

    @Step("Checking that at least one price is not in the selected price range")
    public void assertPricesAreNotInRange(int minPrice, int maxPrice) {
        List<WebElement> productPrices = driver.findElements(filteredItemsPriceLocator);
        boolean foundOutOfRange = productPrices.stream()
                .map(priceElement -> Integer.parseInt(priceElement.getAttribute("data-price-amount")))
                .anyMatch(priceValue -> priceValue <= minPrice || priceValue >= maxPrice);
        assertThat(foundOutOfRange)
                .as("At least one price should be outside the range [%d, %d]", minPrice, maxPrice)
                .isTrue();
    }
}
