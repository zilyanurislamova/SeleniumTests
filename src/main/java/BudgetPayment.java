import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.Keys.ENTER;

public class BudgetPayment extends CommercialPayment {
    private WebDriver driver;
    private By payerStatusField = By.xpath("//span[text() = 'Выберите значение']");
    private By payerStatusOption = By.cssSelector("div[title*='01 - налогоплательщик']");
    private By kbkField = By.cssSelector("input[placeholder='Введите значение КБК']");
    private By oktmoField = By.cssSelector("input[placeholder='Введите значение ОКТМО']");
    private By paymentReasonField = By.cssSelector("input[aria-activedescendant='react-select-6--value']");
    private By taxPeriodList = By.cssSelector("div[data-test-id='TaxPeriod__period--selectDropDown']");
    private By taxPeriodOption = By.cssSelector("div[title='0 - не определено']");
    private By taxDocNumberField = By.cssSelector("input[placeholder='Введите номер документа']");
    private By dateRadioButton = By.cssSelector("label[for='id-4-16']");
    private By uinField = By.cssSelector("input[placeholder='УИН']");

    public BudgetPayment(WebDriver driver) {
        super(driver);
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
        driver.findElement(paymentReasonField).sendKeys(paymentReason + ENTER);
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
        driver.findElement(dateRadioButton).click();
    }

    /**
     * Ввести УИН
     **/
    private void typeUin(String uin) {
        driver.findElement(uinField).sendKeys(uin);
    }

    /**
     * Создать платёж в бюджет
     **/
    public CreatedPayment createBudgetPayment(String amount, String kbk, String oktmo, String paymentReason, String taxDocNumber, String uin) {
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
        clickSaveButton();
        return new CreatedPayment();
    }
}
