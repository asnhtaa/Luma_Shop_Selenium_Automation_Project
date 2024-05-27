package tests;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.WomenTopsPage;

public class FilterItemsTests extends AbstractTest {

    @DisplayName("Check the functionality of filtering clothes by color")
    @Test
    void filterClothesByColorTest() {
        HomePage homePage = new HomePage(driver);

        homePage.openSignInPage();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loggingIn(configReader.getEmail(), configReader.getPassword());

        homePage.clickWomenTops();

        WomenTopsPage womenTopsPage = new WomenTopsPage(driver);

        womenTopsPage.filterItemsByColor("Blue");

        womenTopsPage.checkEachItemIsInSelectedColor();
    }

    @DisplayName("Check the functionality of filtering clothes by price")
    @Test
    void filterClothesByPriceTest() {
        HomePage homePage = new HomePage(driver);

        homePage.openSignInPage();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loggingIn(configReader.getEmail(), configReader.getPassword());

        homePage.clickWomenTops();

        WomenTopsPage womenTopsPage = new WomenTopsPage(driver);

        womenTopsPage.filterItemsByPrice( "$60.00");

        womenTopsPage.checkPricesInRange(60, 69);
    }

    @DisplayName("Check the functionality of clearing filters")
    @Test
    void clearFilterTest() {
        HomePage homePage = new HomePage(driver);

        homePage.openSignInPage();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loggingIn(configReader.getEmail(), configReader.getPassword());

        homePage.clickWomenTops();

        WomenTopsPage womenTopsPage = new WomenTopsPage(driver);

        womenTopsPage.filterItemsByPrice("$60.00");

        womenTopsPage.checkPricesInRange(60, 69);

        womenTopsPage.clickClearAll();

        womenTopsPage.checkPricesNotInRange(60, 69);
    }
}
