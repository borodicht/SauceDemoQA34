package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    String expectedProductName = "Sauce Labs Backpack";
    String expectedPriceOfProduct = "29.99";

    @Test
    @Owner("Borodich T.V.")
    @Epic("Sauce Demo 1")
    @Feature("Cart")
    @Story("Add to cart")
    @Description("Проврка козины")
    @Severity(SeverityLevel.CRITICAL)
    @Flaky
    @Link(name = "Аналитика", url = "https://www.saucedemo.com/")
    @TmsLink("SD-T01")
    @Issue("BUG-01")
    public void checkCart() {
        loginPage.open()
                .isPageOpened()
                .login(user, password)
                .isPageOpened()
                .addToCart("Sauce Labs Backpack")
                .addToCart("Test.allTheThings() T-Shirt (Red)")
                .clickCart();
        assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"), "SO BAAAAAAD");
        assertEquals(cartPage.getProductNameFromCart(0), "Sauce Labs Backpack", "SO BAAAAAD");
        assertEquals(cartPage.getProductNameFromCart(1), "Test.allTheThings() T-Shirt (Red)", "SO BAAAAAD");
        assertTrue(cartPage.getProductsName().contains("Sauce Labs Backpack"), "SO BAAAAAD");
    }
}
