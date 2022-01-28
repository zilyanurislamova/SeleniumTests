import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;

public class LoginTest {
    @Test
    public void shouldLogin() {
        Login loginPage = new Login();
        loginPage.openPage("https://sbi.sberbank.ru:9443/ic/dcb/?#/");
        loginPage.clickDemoLink();
        new WebDriverWait(Login.driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.urlToBe("https://sbi.sberbank.ru:9444/ic/dcb/index.html#/main"));
        assertEquals(Login.driver.getCurrentUrl(), "https://sbi.sberbank.ru:9444/ic/dcb/index.html#/main");
    }
}
