import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerformingTest {
    @Test
    public void shouldPerformPayment() {
        SigningTest paymentToBeSigned = new SigningTest();
        paymentToBeSigned.shouldSignPayment();
        Performing.clickSendButton();
        WebElement successfulSendingNotification = Login.driver.findElement(By.xpath("//h2[text()='Платёжное поручение отправлено']"));
        assertEquals("Платёжное поручение отправлено", successfulSendingNotification.getText());
    }
}
