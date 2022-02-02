import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class PaymentCreation {
    /**
     * Открыть раздел "Счета и платежи"
     **/
    public static void openAccountsPaymentsPage() {
        WebElement accountsPaymentsOption = Login.driver.findElement(By.cssSelector("button[aria-label=\"Счета и платежи\"]"));
        accountsPaymentsOption.click();
    }

    /**
     * Нажать на кнопку "Новый платёж"
     **/
    public static void clickNewPaymentButton() {
        Login.driver.findElement(By.className("button-wrapper")).click();
        WebElement newPaymentButton = Login.driver.findElement(By.cssSelector(".dropdown-new-payment.dropdown-toggle.btn.btn-success"));
        newPaymentButton.click();
    }

    /**
     * Выбрать тип платежа
     **/
    public static void selectPaymentType(PaymentTypes paymentType) {
        switch (paymentType) {
            case COMMERCIALPAYMENT:
                WebElement commercialPaymentOption = Login.driver.findElement(By.cssSelector("ul[role='menu'] li:first-child a"));
                commercialPaymentOption.click();
                break;
            case BUDGETPAYMENT:
                WebElement budgetPaymentOption = Login.driver.findElement(By.cssSelector("ul[role='menu'] li:nth-child(3) a"));
                budgetPaymentOption.click();
                break;
            case BETWEENACCOUNTS:
                WebElement paymentBetweenAccountsOption = Login.driver.findElement(By.cssSelector("ul[role='menu'] li:nth-child(5) a"));
                paymentBetweenAccountsOption.click();
                break;
        }
    }

    /**
     * Открыть форму создания платежа
     **/
    public static void openPaymentCreationForm(PaymentTypes paymentType) {
        openAccountsPaymentsPage();
        clickNewPaymentButton();
        selectPaymentType(paymentType);
    }
}
