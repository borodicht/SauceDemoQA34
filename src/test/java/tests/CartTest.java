package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    String expectedProductName = "Sauce Labs Backpack";
    String expectedPriceOfProduct = "29.99";

    @Test
    public void checkCart() {
        //логинимся
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        //добавляем товары в корзину
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)");

        //нажимаем на кнопку корзина
        productsPage.clickCart();

        assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"), "SO BAAAAAAD");
        assertEquals(cartPage.getProductNameFromCart(0), "Sauce Labs Backpack", "SO BAAAAAD");
        assertEquals(cartPage.getProductNameFromCart(1), "Test.allTheThings() T-Shirt (Red)", "SO BAAAAAD");
        assertTrue(cartPage.getProductsName().contains("Sauce Labs Backpack"), "SO BAAAAAD");
    }
}
