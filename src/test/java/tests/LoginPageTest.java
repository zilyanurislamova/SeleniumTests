package tests;

import tests.annotations.Regression;
import tests.annotations.Smoke;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.MainPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Логин")
class LoginPageTest {
    WebDriver driver;
    LoginPage loginPage;
    MainPage mainPage;
    By alert = By.cssSelector("div[class*=alert]");

    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sbi.sberbank.ru:9443/ic/dcb/?#/");
    }

    @Test
    @Smoke
    @DisplayName("Вход в демо-режим")
    void testLogin() {
        loginPage = new LoginPage(driver);
        mainPage = loginPage.login();
        assertEquals("Иванов Иван Иванович", mainPage.getDemoAccountName());
        System.out.println("Выполнен вход в демо-режим");
    }

    @Test
    @Smoke
    @DisplayName("Выход")
    void testLogout() {
        loginPage = new LoginPage(driver);
        mainPage = loginPage.login();
        loginPage = mainPage.logout();
        Assertions.assertThrows(NoSuchElementException.class, () -> mainPage.getDemoAccountName());
    }

    @Test
    @Regression
    @DisplayName("Вход в личный кабинет с невалидными данными")
    void testSignInWithInvalidCredentials() {
        loginPage = new LoginPage(driver);
        mainPage = loginPage.signIn("fakeLogin", "fakePassword");
        assertEquals("Логин или пароль неверны.", driver.findElement(alert).getText());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
