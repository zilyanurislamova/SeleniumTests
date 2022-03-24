package pages.payments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CreatedPaymentPage;
import pages.enums.PaymentType;

import static org.openqa.selenium.Keys.DOWN;
import static org.openqa.selenium.Keys.ENTER;

public class PaymentBetweenAccounts extends CommercialPayment {
    private final PaymentType paymentType = PaymentType.BETWEENACCOUNTS;
    private final By payeeField = payerField;

    public PaymentBetweenAccounts(WebDriver driver) {
        super(driver);
        if (!driver.findElement(pageHeader).getText().equals("Перевод между своими счетами")) {
            throw new IllegalStateException("This is not PaymentBetweenAccounts");
        }
    }

    /**
     * Создать платёж между своими счетами
     **/
    public CreatedPaymentPage createPayment(String amount) {
        super.createPayment(amount, "", "");
        return new CreatedPaymentPage(driver, paymentType);
    }

    /**
     * Выбрать счёт зачисления
     **/
    @Override
    public void selectPayee(String payeeName) {
        driver.findElements(payeeField).get(1).sendKeys(DOWN, ENTER);
    }
}
