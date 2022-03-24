package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.enums.PaymentType;
import pages.payments.BudgetPayment;
import pages.payments.CommercialPayment;
import pages.payments.PaymentBetweenAccounts;

import java.util.List;


public class AccountsPaymentsPage {
    private final WebDriver driver;
    private final By pageHeader = By.cssSelector("h1");
    private final By buttonWrapper = By.className("button-wrapper");
    private final By newPaymentButton = By.cssSelector(".dropdown-new-payment.dropdown-toggle.btn.btn-success");
    private final By paymentOptions = By.cssSelector("ul[role='menu'] li a");

    public AccountsPaymentsPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.findElement(pageHeader).getText().equals("Счета и платежи")) {
            throw new IllegalStateException("This is not AccountsPaymentsPage");
        }
    }

    /**
     * Нажать на кнопку "Новый платёж"
     **/
    private void clickNewPaymentButton() {
        driver.findElement(buttonWrapper).click();
        driver.findElement(newPaymentButton).click();
    }

    /**
     * Выбрать тип платежа
     **/
    private CommercialPayment selectPaymentType(PaymentType paymentType) {
        List<WebElement> options = driver.findElements(paymentOptions);
        switch (paymentType) {
            case COMMERCIALPAYMENT:
                options.get(0).click();
                return new CommercialPayment(driver);
            case BUDGETPAYMENT:
                options.get(2).click();
                return new BudgetPayment(driver);
            case BETWEENACCOUNTS:
                options.get(4).click();
                return new PaymentBetweenAccounts(driver);
        }
        return null;
    }

    /**
     * Открыть форму создания платежа
     **/
    public CommercialPayment openPaymentCreationPage(PaymentType paymentType) {
        clickNewPaymentButton();
        return selectPaymentType(paymentType);
    }
}
