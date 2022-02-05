import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SigningTest {
    @BeforeEach
    void provideSigningPreconditions() {
        PaymentCreationTest paymentToBeCreated = new PaymentCreationTest();
        paymentToBeCreated.provideCreationPreconditions();
        paymentToBeCreated.shouldCreatePaymentBetweenAccounts();
    }
    
    @Test
    @DisplayName("Подписание платёжного поручения")
    void shouldSignPayment() {
        Signing.signPayment("11111");
        WebElement successfulSigningNotification = Login.driver.findElement(By.xpath("//h2[text()='Платёжное поручение подписано']"));
        assertEquals("Подписан", Login.driver.findElement(By.cssSelector(".text-status")).getText());
        System.out.println(successfulSigningNotification.getText());
    }
}
