import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.Keys.ENTER;

public class BudgetPayment extends PaymentCreation {
    private WebDriver driver;
    /**
     * Выбрать статус плательщика
     **/
    private void selectPayerStatus() {
        WebElement payerStatusField = driver.findElement(By.xpath("//span[text() = 'Выберите значение']"));
        payerStatusField.click();
        WebElement payerStatusOption = driver.findElement(By.cssSelector("div[title*='01 - налогоплательщик']"));
        payerStatusOption.click();
    }

    /**
     * Ввести КБК
     **/
    private void typeKbk(String kbk) {
        WebElement kbkField = driver.findElement(By.cssSelector("input[placeholder='Введите значение КБК']"));
        kbkField.sendKeys(kbk);
    }

    /**
     * Ввести ОКТМО
     **/
    private void typeOktmo(String oktmo) {
        WebElement kbkField = driver.findElement(By.cssSelector("input[placeholder='Введите значение ОКТМО']"));
        kbkField.sendKeys(oktmo);
    }

    /**
     * Выбрать основание платежа
     **/
    private void selectPaymentReason(String paymentReason) {
        WebElement paymentReasonField = driver.findElement(By.cssSelector("input[aria-activedescendant='react-select-6--value']"));
        paymentReasonField.sendKeys(paymentReason + ENTER);
    }

    /**
     * Выбрать налоговый период
     **/
    private void selectTaxPeriod() {
        WebElement taxPeriodList = driver.findElement(By.cssSelector("div[data-test-id='TaxPeriod__period--selectDropDown']"));
        taxPeriodList.click();
        WebElement taxPeriodOption = driver.findElement(By.cssSelector("div[title='0 - не определено']"));
        taxPeriodOption.click();
    }

    /**
     * Ввести номер документа
     **/
    private void typeTaxDocNumber(String taxDocNumber) {
        WebElement docNumberField = driver.findElement(By.cssSelector("input[placeholder='Введите номер документа']"));
        docNumberField.sendKeys(taxDocNumber);
    }

    /**
     * Указать дату
     **/
    private void selectDate() {
        WebElement dateRadioButton = driver.findElement(By.cssSelector("label[for='id-4-16']"));
        dateRadioButton.click();
    }

    /**
     * Ввести УИН
     **/
    private void typeUin(String uin) {
        WebElement uinField = driver.findElement(By.cssSelector("input[placeholder='УИН']"));
        uinField.sendKeys(uin);
    }

    /**
     * Создать платёж в бюджет
     **/
    public void createBudgetPayment(String amount, String kbk, String oktmo, String paymentReason, String taxDocNumber, String uin) {
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
    }
}
