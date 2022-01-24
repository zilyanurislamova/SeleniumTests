import org.junit.*;
import org.openqa.selenium.*;

import static org.junit.Assert.*;

public class SigningTest {
    @Test
    public void shouldSignPayment(){
        PaymentCreationTest paymentToBeCreated = new PaymentCreationTest();
        paymentToBeCreated.shouldCreatePaymentBetweenAccounts();
        Signing.signPayment(new Signing(), "11111");
        WebElement successfulSigningNotification = Login.driver.findElement(By.xpath("//div[text()='Платёжное поручение подписано']"));
        assertEquals("Платёжное поручение подписано", successfulSigningNotification.getText());
    }
}
