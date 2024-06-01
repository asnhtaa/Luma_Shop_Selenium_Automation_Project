package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.WomenTopsPage;

public class FilterItemsTests extends AbstractTest {

    @DisplayName("When the user applies a color filter, only items matching the selected color are displayed")
    @Test
    void filterItemsByColorTest() {
        HomePage homePage = new HomePage(driver);

        homePage.openSignInPage();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loggingIn(envProperties.getProperty("user_email"), envProperties.getProperty("user_password"));

        homePage.clickWomenTops();

        WomenTopsPage womenTopsPage = new WomenTopsPage(driver);

        womenTopsPage.filterItemsByColor("Blue");

        womenTopsPage.assertEachItemIsInSelectedColor();
    }

    @DisplayName("When the user applies a price filter, only items within the specified price range are displayed")
    @Test
    void filterItemsByPriceTest() {
        HomePage homePage = new HomePage(driver);

        homePage.openSignInPage();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loggingIn(envProperties.getProperty("user_email"), envProperties.getProperty("user_password"));

        homePage.clickWomenTops();

        WomenTopsPage womenTopsPage = new WomenTopsPage(driver);

        womenTopsPage.assertPricesAreNotInRange(60, 69);

        womenTopsPage.filterItemsByPrice("$60.00");

        womenTopsPage.assertPricesAreInSelectedRange(60, 69);
    }

    @DisplayName("When the user clears the price filter, items outside the previous price range are displayed")
    @Test
    void clearFilterFunctionalityTest() {
        HomePage homePage = new HomePage(driver);

        homePage.openSignInPage();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loggingIn(envProperties.getProperty("user_email"), envProperties.getProperty("user_password"));

        homePage.clickWomenTops();

        WomenTopsPage womenTopsPage = new WomenTopsPage(driver);

        womenTopsPage.assertPricesAreNotInRange(60, 69);

        womenTopsPage.filterItemsByPrice("$60.00");

        womenTopsPage.assertPricesAreInSelectedRange(60, 69);

        womenTopsPage.clickClearAll();

        womenTopsPage.assertPricesAreNotInRange(60, 69);
    }
}
