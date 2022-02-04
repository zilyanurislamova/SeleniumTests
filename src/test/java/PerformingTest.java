import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerformingTest {
    SigningTest paymentToBeSigned = new SigningTest();

    @Test
    public void shouldPerformPayment() {
        paymentToBeSigned.shouldSignPayment();
        Performing.clickSendButton();
        Login.driver.findElement(By.xpath("//h2[text()='Платёжное поручение отправлено']"));
        assertEquals("Доставлен", Login.driver.findElement(By.cssSelector(".text-status")).getText());
    }
}
