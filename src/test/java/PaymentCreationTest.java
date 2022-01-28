import com.google.errorprone.annotations.InlineMeValidationDisabled;
import org.junit.*;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import static org.junit.Assert.*;

public class PaymentCreationTest {
    @Test
    @BeforeEach
    public void shouldDoBeforeEach(){
        Login.login(new Login(),"https://sbi.sberbank.ru:9443/ic/dcb/?#/");
    }

    @Test
    public void shouldCreateCommercialPayment(){
        PaymentCreation accountsPaymentsPage = new PaymentCreation();
        accountsPaymentsPage.openAccountsPaymentsPage();
        accountsPaymentsPage.clickNewPaymentButton();
        accountsPaymentsPage.selectPaymentType(PaymentTypes.COMMERCIALPAYMENT);
        CommercialPayment.createCommercialPayment(new CommercialPayment(), "77,78", "счет", "контр");
        WebElement successfulCreationNotification = Login.driver.findElement(By.xpath("//h2[text()='Платёжное поручение создано']"));
        assertEquals("Платёжное поручение создано", successfulCreationNotification.getText());
        System.out.println("Платёж контрагенту успешно создан");
    }

    @Test
    @Ignore
    public void shouldCreateBudgetPayment(){
        Login.login(new Login(),"https://sbi.sberbank.ru:9443/ic/dcb/?#/");
        PaymentCreation accountsPaymentsPage = new PaymentCreation();
        accountsPaymentsPage.openAccountsPaymentsPage();
        accountsPaymentsPage.clickNewPaymentButton();
        accountsPaymentsPage.selectPaymentType(PaymentTypes.BUDGETPAYMENT);
        BudgetPayment.createBudgetPayment(new BudgetPayment(), "77,78", "БЮДЖЕТ", "0", "0", "0");
        WebElement successfulCreationNotification = Login.driver.findElement(By.xpath("//div[text()='Платёжное поручение создано']"));
        assertEquals("Платёжное поручение создано", successfulCreationNotification.getText());
        System.out.println("Платёж в бюджет успешно создан");
    }


    @Test
    @Ignore
    public void shouldCreateHousingPayment(){
        Login.login(new Login(),"https://sbi.sberbank.ru:9443/ic/dcb/?#/");
        PaymentCreation accountsPaymentsPage = new PaymentCreation();
        accountsPaymentsPage.openAccountsPaymentsPage();
        accountsPaymentsPage.clickNewPaymentButton();
        accountsPaymentsPage.selectPaymentType(PaymentTypes.HOUSING);
        HousingPayment.createHousingPayment(new HousingPayment(), "77,78", "ЖКУ");
        WebElement successfulCreationNotification = Login.driver.findElement(By.xpath("//div[text()='Платёжное поручение создано']"));
        assertEquals("Платёжное поручение создано", successfulCreationNotification.getText());
        System.out.println("Платёж ЖКУ успешно создан");
    }


    @Test
    @Ignore
    public void shouldCreatePaymentBetweenAccounts(){
        Login.login(new Login(),"https://sbi.sberbank.ru:9443/ic/dcb/?#/");
        PaymentCreation accountsPaymentsPage = new PaymentCreation();
        accountsPaymentsPage.openAccountsPaymentsPage();
        accountsPaymentsPage.clickNewPaymentButton();
        accountsPaymentsPage.selectPaymentType(PaymentTypes.BETWEENACCOUNTS);
        PaymentBetweenAccounts.createPaymentBetweenAccounts(new PaymentBetweenAccounts(), "77,78");
        WebElement successfulCreationNotification = Login.driver.findElement(By.xpath("//div[text()='Платёжное поручение создано']"));
        assertEquals("Платёжное поручение создано", successfulCreationNotification.getText());
        System.out.println("Платёж между своими счетами успешно создан");
    }
}

