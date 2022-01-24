import org.junit.*;
import org.openqa.selenium.*;

import static org.junit.Assert.*;

public class PaymentCreationTest {
    @Test
    public void shouldCreateCommercialPayment(){
        Login.login(new Login(),"http://10.36.252.39:9080/ic/dcb/login.html?ReturnTo=%2Faccounts-payments#/", "riart_ch", "zaq!xsw", "1");
        PaymentCreation accountsPaymentsPage = new PaymentCreation();
        accountsPaymentsPage.closeWarning();
        accountsPaymentsPage.clickNewPaymentButton();
        accountsPaymentsPage.selectPaymentType(PaymentTypes.COMMERCIALPAYMENT);
        CommercialPayment.createCommercialPayment(new CommercialPayment(), "77,78", "ПАО");
        WebElement successfulCreationNotification = Login.driver.findElement(By.xpath("//div[text()='Платёжное поручение создано']"));
        assertEquals("Платёжное поручение создано", successfulCreationNotification.getText());
        System.out.println("Платёж контрагенту успешно создан");
    }

    @Test
    public void shouldCreateBudgetPayment(){
        Login.login(new Login(),"http://10.36.252.39:9080/ic/dcb/login.html?ReturnTo=%2Faccounts-payments#/", "riart_ch", "zaq!xsw", "1");
        PaymentCreation accountsPaymentsPage = new PaymentCreation();
        accountsPaymentsPage.closeWarning();
        accountsPaymentsPage.clickNewPaymentButton();
        accountsPaymentsPage.selectPaymentType(PaymentTypes.BUDGETPAYMENT);
        BudgetPayment.createBudgetPayment(new BudgetPayment(), "77,78", "БЮДЖЕТ", "0", "0", "0");
        WebElement successfulCreationNotification = Login.driver.findElement(By.xpath("//div[text()='Платёжное поручение создано']"));
        assertEquals("Платёжное поручение создано", successfulCreationNotification.getText());
        System.out.println("Платёж в бюджет успешно создан");
    }

    @Test
    public void shouldCreateHousingPayment(){
        Login.login(new Login(),"http://10.36.252.39:9080/ic/dcb/login.html?ReturnTo=%2Faccounts-payments#/", "riart_ch", "zaq!xsw", "1");
        PaymentCreation accountsPaymentsPage = new PaymentCreation();
        accountsPaymentsPage.closeWarning();
        accountsPaymentsPage.clickNewPaymentButton();
        accountsPaymentsPage.selectPaymentType(PaymentTypes.HOUSING);
        HousingPayment.createHousingPayment(new HousingPayment(), "77,78", "ЖКУ");
        WebElement successfulCreationNotification = Login.driver.findElement(By.xpath("//div[text()='Платёжное поручение создано']"));
        assertEquals("Платёжное поручение создано", successfulCreationNotification.getText());
        System.out.println("Платёж ЖКУ успешно создан");
    }

    @Test
    public void shouldCreatePaymentBetweenAccounts(){
        Login.login(new Login(),"http://10.36.252.39:9080/ic/dcb/login.html?ReturnTo=%2Faccounts-payments#/", "riart_ch", "zaq!xsw", "1");
        PaymentCreation accountsPaymentsPage = new PaymentCreation();
        accountsPaymentsPage.closeWarning();
        accountsPaymentsPage.clickNewPaymentButton();
        accountsPaymentsPage.selectPaymentType(PaymentTypes.BETWEENACCOUNTS);
        PaymentBetweenAccounts.createPaymentBetweenAccounts(new PaymentBetweenAccounts(), "77,78");
        WebElement successfulCreationNotification = Login.driver.findElement(By.xpath("//div[text()='Платёжное поручение создано']"));
        assertEquals("Платёжное поручение создано", successfulCreationNotification.getText());
        System.out.println("Платёж между своими счетами успешно создан");
    }
}

