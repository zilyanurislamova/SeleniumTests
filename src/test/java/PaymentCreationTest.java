import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentCreationTest {
    PaymentCreation paymentCreation = new PaymentCreation();
    @BeforeEach
    void provideCreationPreconditions() {
        Login.login("https://sbi.sberbank.ru:9443/ic/dcb/?#/");
    }

    @Test
    @DisplayName("Создание платежа контрагенту")
    void shouldCreateCommercialPayment() {
        paymentCreation.openPaymentCreationForm(PaymentTypes.COMMERCIALPAYMENT);
        CommercialPayment commercialPayment = new CommercialPayment();
        commercialPayment.createCommercialPayment("77,78", "счет", "контр");
        assertEquals("Создан", Login.driver.findElement(By.cssSelector(".text-status")).getText());
        System.out.println("Платёж контрагенту успешно создан");
    }

    @Test
    @DisplayName("Создание платежа в бюджет")
    void shouldCreateBudgetPayment() {
        paymentCreation.openPaymentCreationForm(PaymentTypes.BUDGETPAYMENT);
        PayeeCreation payeeCreation = new PayeeCreation();
        payeeCreation.createPayee("БЮДЖЕТНАЯ ОРГАНИЗАЦИЯ", "7806000792", "780601001", "40101810523456789101", "041946000");
        BudgetPayment budgetPayment = new BudgetPayment();
        budgetPayment.createBudgetPayment("2022,02", "18210101011011000110", "19730000", "0", "108", "0");
        assertEquals("Создан", Login.driver.findElement(By.cssSelector(".text-status")).getText());
        System.out.println("Платёж в бюджет успешно создан");
    }

    @Test
    @DisplayName("Создание платежа между своими счетами")
    void shouldCreatePaymentBetweenAccounts() {
        paymentCreation.openPaymentCreationForm(PaymentTypes.BETWEENACCOUNTS);
        PaymentBetweenAccounts paymentBetweenAccounts = new PaymentBetweenAccounts();
        paymentBetweenAccounts.createPaymentBetweenAccounts("22988,50");
        assertEquals("Создан", Login.driver.findElement(By.cssSelector(".text-status")).getText());
        System.out.println("Платёж между своими счетами успешно создан");
    }
}

