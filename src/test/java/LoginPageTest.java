import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Логин")
class LoginPageTest {
    WebDriver driver;
    LoginPage loginPage;
    MainPage mainPage;

    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sbi.sberbank.ru:9443/ic/dcb/?#/");
    }

    @Test
    @DisplayName("Вход в демо-режим")
    void testLogin() {
        loginPage = new LoginPage(driver);
        mainPage = loginPage.login();
        assertEquals("Иванов Иван Иванович", mainPage.getDemoAccountName());
        System.out.println("Выполнен вход в демо-режим");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
