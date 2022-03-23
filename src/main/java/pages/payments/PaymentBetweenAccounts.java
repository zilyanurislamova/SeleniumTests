package pages.payments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CreatedPaymentPage;
import pages.enums.PaymentType;

import static org.openqa.selenium.Keys.DOWN;
import static org.openqa.selenium.Keys.ENTER;

public class PaymentBetweenAccounts extends CommercialPayment {
    private final PaymentType paymentType = PaymentType.BETWEENACCOUNTS;
    private final By payerField = By.cssSelector("input[aria-activedescendant='react-select-4--value']");
    private final By payeeField = By.cssSelector("input[aria-activedescendant='react-select-5--value']");
    private final By sumField = By.cssSelector("input[placeholder='0,00']");

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
        selectPayer("");
        selectPayee("");
        typeSum(amount);
        clickCreateButton();
        clickSaveButton();
        return new CreatedPaymentPage(driver, paymentType);
    }

    /**
     * Редактировать платёжное поручение
     **/
    @Override
    public CreatedPaymentPage editPayment(String amount) {
        driver.findElement(sumField).clear();
        typeSum(amount);
        clickSaveEditsButton();
        clickSaveButton();
        return new CreatedPaymentPage(driver, paymentType);
    }

    /**
     * Выбрать счёт списания
     **/
    @Override
    public void selectPayer(String payeePartialName) {
        driver.findElement(payerField).sendKeys(ENTER);
    }

    /**
     * Выбрать счёт зачисления
     **/
    @Override
    public void selectPayee(String payerPartialName) {
        driver.findElement(payeeField).sendKeys(DOWN, ENTER);
    }

    /**
     * Ввести сумму
     **/
    @Override
    public void typeSum(String amount) {
        driver.findElement(sumField).sendKeys(amount);
    }
}
