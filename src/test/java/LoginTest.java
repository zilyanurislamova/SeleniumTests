import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    @Test
    public void shouldLogin() {
        Login.login(new Login(), "https://sbi.sberbank.ru:9443/ic/dcb/?#/");
        new WebDriverWait(Login.driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.urlToBe("https://sbi.sberbank.ru:9444/ic/dcb/index.html#/main"));
        assertEquals(Login.driver.getCurrentUrl(), "https://sbi.sberbank.ru:9444/ic/dcb/index.html#/main");
    }
}
