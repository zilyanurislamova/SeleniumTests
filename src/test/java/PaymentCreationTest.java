import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class PaymentCreationTest {
    @Test
    public void shouldCreateCommercialPayment(){
        Login.login(new Login(),"https://sbi.sberbank.ru:9443/ic/dcb/?#/");
        PaymentCreation.openPaymentCreationForm(new PaymentCreation(), PaymentTypes.COMMERCIALPAYMENT);
        CommercialPayment.createCommercialPayment(new CommercialPayment(), "77,78", "счет", "контр");
        WebElement successfulCreationNotification = Login.driver.findElement(By.xpath("//h2[text()='Платёжное поручение создано']"));
        assertEquals("Платёжное поручение создано", successfulCreationNotification.getText());
        System.out.println("Платёж контрагенту успешно создан");
    }

    @Test
    public void shouldCreateBudgetPayment(){
        Login.login(new Login(),"https://sbi.sberbank.ru:9443/ic/dcb/?#/");
        PaymentCreation.openPaymentCreationForm(new PaymentCreation(), PaymentTypes.BUDGETPAYMENT);
        BudgetPayment.createBudgetPayment(new BudgetPayment(), "77,78", "18210101011011000110", "19730000", "0", "777", "0");
        WebElement successfulCreationNotification = Login.driver.findElement(By.xpath("//h2[text()='Платёжное поручение создано']"));
        assertEquals("Платёжное поручение создано", successfulCreationNotification.getText());
        System.out.println("Платёж в бюджет успешно создан");
    }

    @Test
    public void shouldCreatePaymentBetweenAccounts(){
        Login.login(new Login(),"https://sbi.sberbank.ru:9443/ic/dcb/?#/");
        PaymentCreation.openPaymentCreationForm(new PaymentCreation(), PaymentTypes.BETWEENACCOUNTS);
        PaymentBetweenAccounts.createPaymentBetweenAccounts(new PaymentBetweenAccounts(), "22988,50");
        WebElement successfulCreationNotification = Login.driver.findElement(By.xpath("//h2[text()='Платёжное поручение создано']"));
        assertEquals("Платёжное поручение создано", successfulCreationNotification.getText());
        System.out.println("Платёж между своими счетами успешно создан");
    }
}

