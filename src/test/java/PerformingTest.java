import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerformingTest {
    @BeforeEach
    void providePerformingPreconditions() {
        SigningTest paymentToBeSigned = new SigningTest();
        paymentToBeSigned.provideSigningPreconditions();
        paymentToBeSigned.shouldSignPayment();
    }

    @Test
    @DisplayName("Отправка платёжного поручения на исполнение")
    void shouldPerformPayment() {
        Performing.clickSendButton();
        WebElement successfulSendingNotification = Login.driver.findElement(By.xpath("//h2[text()='Платёжное поручение отправлено']"));
        assertEquals("Доставлен", Login.driver.findElement(By.cssSelector(".text-status")).getText());
        System.out.println(successfulSendingNotification.getText());
    }
}
