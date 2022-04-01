package pages.payments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CreatedPaymentPage;
import pages.enums.PaymentType;

import static org.openqa.selenium.Keys.ENTER;

public class BudgetPayment extends CommercialPayment {
    private final PaymentType paymentType = PaymentType.BUDGETPAYMENT;
    private final By payerStatusField = By.xpath("//span[text()='Выберите значение']");
    private final By payerStatusOption = By.cssSelector("[title*='01 - налогоплательщик']");
    private final By kbkField = By.cssSelector("[placeholder*='КБК']");
    private final By oktmoField = By.cssSelector("[placeholder*='ОКТМО']");
    private final By paymentReasonField = payerField;
    private final By taxPeriodList = By.cssSelector("[data-test-id='TaxPeriod__period--selectDropDown']");
    private final By taxPeriodOption = By.cssSelector("[title='0 - не определено']");
    private final By taxDocNumberField = By.cssSelector("[placeholder='Введите номер документа']");
    private final By dateRadioButton = By.cssSelector("label[for*='id']");
    private final By uinField = By.cssSelector("[placeholder='УИН']");

    public BudgetPayment(WebDriver driver) {
        super(driver);
        if (!driver.findElement(pageHeader).getText().equals("Рублёвый платёж в бюджет")) {
            throw new IllegalStateException("This is not BudgetPayment");
        }
    }

    /**
     * Создать платёж в бюджет
     **/
    public CreatedPaymentPage createPayment(String amount, String kbk, String oktmo, String paymentReason, String taxDocNumber, String uin) {
        typeSum(amount);
        selectPayer("");
        selectPayerStatus();
        typeKbk(kbk);
        typeOktmo(oktmo);
        selectPaymentReason(paymentReason);
        selectTaxPeriod();
        typeTaxDocNumber(taxDocNumber);
        selectDate();
        typeUin(uin);
        clickCreateButton();
        submitSave();
        return new CreatedPaymentPage(driver, paymentType);
    }

    /**
     * Выбрать статус плательщика
     **/
    private void selectPayerStatus() {
        driver.findElement(payerStatusField).click();
        driver.findElement(payerStatusOption).click();
    }

    /**
     * Ввести КБК
     **/
    private void typeKbk(String kbk) {
        driver.findElement(kbkField).sendKeys(kbk);
    }

    /**
     * Ввести ОКТМО
     **/
    private void typeOktmo(String oktmo) {
        driver.findElement(oktmoField).sendKeys(oktmo);
    }

    /**
     * Выбрать основание платежа
     **/
    private void selectPaymentReason(String paymentReason) {
        driver.findElements(paymentReasonField).get(2).sendKeys(paymentReason + ENTER);
    }

    /**
     * Выбрать налоговый период
     **/
    private void selectTaxPeriod() {
        driver.findElement(taxPeriodList).click();
        driver.findElement(taxPeriodOption).click();
    }

    /**
     * Ввести номер документа
     **/
    private void typeTaxDocNumber(String taxDocNumber) {
        driver.findElement(taxDocNumberField).sendKeys(taxDocNumber);
    }

    /**
     * Указать дату
     **/
    private void selectDate() {
        driver.findElements(dateRadioButton).get(1).click();
    }

    /**
     * Ввести УИН
     **/
    private void typeUin(String uin) {
        driver.findElement(uinField).sendKeys(uin);
    }
}
