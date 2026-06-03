package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Retry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(
            description = "Проверка логина с позитивным именем пользователя и паролем",
            testName = "Проверка логина с позитивным именем пользователя и паролем",
            groups = {"smoke", "slow", "login"},
            retryAnalyzer = Retry.class
    )
    @Owner("Borodich T.V.")
    @Epic("Sauce Demo 1")
    @Feature("Log in")
    @Story("Log in with positive credential")
    @Description("Проверка логина с позитивным именем пользователя и паролем")
    @Severity(SeverityLevel.CRITICAL)
    @Flaky
    @Link(name = "Аналитика", url = "https://www.saucedemo.com/")
    @TmsLink("SD-T01")
    @Issue("BUG-01")
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(productsPage.getTitle(),
                "Products",
                "SO BAAAAAAD");
    }

    @Test(description = "Проверка логина с пустым именем пользователя",
            testName = "Проверка логина с пустым именем пользователя", groups = "regression")
    public void chekLoginWithEmptyUserName() {
        loginPage.open();
        loginPage.loginWithNegativeCred("", password);
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "SO BAAAAAD");
    }

    @Test(priority = 3, groups = "regression")
    public void chekLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.loginWithNegativeCred(user, "");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Password is required",
                "SO BBAAAAD");
    }

    @Test(enabled = false)
    public void chekLoginWithNegativeCred() {
        loginPage.open();
        loginPage.loginWithNegativeCred("test", "test");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "SO BAAAAD");
    }


    @DataProvider(name = "Параметризированный тест для негативного логина", indices = {0, 2})
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "Параметризированный тест для негативного логина")
    public void chekLoginWithNegativeCred1(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.loginWithNegativeCred(user, password);
        assertEquals(loginPage.getErrorMessage(),
                errorMessage,
                "SO BAAAAD");
        assertThat(loginPage.getErrorMessage()).isEqualTo(errorMessage);
    }
}
