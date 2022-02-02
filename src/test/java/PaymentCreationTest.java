import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class PaymentCreationTest {
    @Test
    public void shouldCreateCommercialPayment() {
        Login.login("https://sbi.sberbank.ru:9443/ic/dcb/?#/");
        PaymentCreation.openPaymentCreationForm(PaymentTypes.COMMERCIALPAYMENT);
        CommercialPayment.createCommercialPayment("77,78", "счет", "контр");
        WebElement successfulCreationNotification = Login.driver.findElement(By.xpath("//h2[text()='Платёжное поручение создано']"));
        assertEquals("Платёжное поручение создано", successfulCreationNotification.getText());
        System.out.println("Платёж контрагенту успешно создан");
    }

    @Test
    public void shouldCreateBudgetPayment() {
        Login.login("https://sbi.sberbank.ru:9443/ic/dcb/?#/");
        PaymentCreation.openPaymentCreationForm(PaymentTypes.BUDGETPAYMENT);
        BudgetPayment.createPayee("Бюджет", "7806000792", "780601001", "40101810523456789101", "041946000");
        BudgetPayment.createBudgetPayment("77,78", "18210101011011000110", "19730000", "0", "777", "0");
        WebElement successfulCreationNotification = Login.driver.findElement(By.xpath("//h2[text()='Платёжное поручение создано']"));
        assertEquals("Платёжное поручение создано", successfulCreationNotification.getText());
        System.out.println("Платёж в бюджет успешно создан");
    }

    @Test
    public void shouldCreatePaymentBetweenAccounts() {
        Login.login("https://sbi.sberbank.ru:9443/ic/dcb/?#/");
        PaymentCreation.openPaymentCreationForm(PaymentTypes.BETWEENACCOUNTS);
        PaymentBetweenAccounts.createPaymentBetweenAccounts("22988,50");
        WebElement successfulCreationNotification = Login.driver.findElement(By.xpath("//h2[text()='Платёжное поручение создано']"));
        assertEquals("Платёжное поручение создано", successfulCreationNotification.getText());
        System.out.println("Платёж между своими счетами успешно создан");
    }
}

