import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    @Test
    @DisplayName("Вход в демо-режим")
    void shouldLogin() {
        Login.login("https://sbi.sberbank.ru:9443/ic/dcb/?#/");
        new WebDriverWait(Login.driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.urlToBe("https://sbi.sberbank.ru:9444/ic/dcb/index.html#/main"));
        assertEquals("https://sbi.sberbank.ru:9444/ic/dcb/index.html#/main", Login.driver.getCurrentUrl());
        System.out.println("Выполнен вход в демо-режим");
    }
}
