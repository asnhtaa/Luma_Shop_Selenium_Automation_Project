package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.WomenTopsPage;

public class FilterItemsTests extends AbstractTest {

    @DisplayName("Check the functionality of filtering clothes by color")
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

    @DisplayName("Check the functionality of filtering clothes by price")
    @Test
    void filterItemsByPriceTest() {
        HomePage homePage = new HomePage(driver);

        homePage.openSignInPage();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loggingIn(envProperties.getProperty("user_email"), envProperties.getProperty("user_password"));

        homePage.clickWomenTops();

        WomenTopsPage womenTopsPage = new WomenTopsPage(driver);

        womenTopsPage.filterItemsByPrice("$60.00");

        womenTopsPage.assertPricesAreInSelectedRange(60, 69);
    }

    @DisplayName("Check the functionality of clearing filters")
    @Test
    void clearFilterFunctionalityTest() {
        HomePage homePage = new HomePage(driver);

        homePage.openSignInPage();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loggingIn(envProperties.getProperty("user_email"), envProperties.getProperty("user_password"));

        homePage.clickWomenTops();

        WomenTopsPage womenTopsPage = new WomenTopsPage(driver);

        womenTopsPage.filterItemsByPrice("$60.00");

        womenTopsPage.assertPricesAreInSelectedRange(60, 69);

        womenTopsPage.clickClearAll();

        womenTopsPage.assertPriceFilterIsCleared(60, 69);
    }
}
