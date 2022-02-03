import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.Keys.ENTER;

public class BudgetPayment {
    /**
     * Ввести сумму
     **/
    private static void typeSum(String amount) {
        WebElement sumField = Login.driver.findElement(By.cssSelector("input[name=\"amount\"]"));
        sumField.sendKeys(amount);
    }

    /**
     * Выбрать плательщика
     **/
    private static void selectPayer() {
        WebElement payerField = Login.driver.findElement(By.cssSelector("input[aria-activedescendant=\"react-select-5--value\"]"));
        payerField.sendKeys(ENTER);
    }

    /**
     * Выбрать статус плательщика
     **/
    private static void selectPayerStatus() {
        WebElement payerStatusField = Login.driver.findElement(By.xpath("//span[text() = 'Выберите значение']"));
        payerStatusField.click();
        WebElement payerStatusOption = Login.driver.findElement(By.cssSelector("div[title*='01 - налогоплательщик']"));
        payerStatusOption.click();
    }

    /**
     * Ввести КБК
     **/
    private static void typeKbk(String kbk) {
        WebElement kbkField = Login.driver.findElement(By.cssSelector("input[placeholder='Введите значение КБК']"));
        kbkField.sendKeys(kbk);
    }

    /**
     * Ввести ОКТМО
     **/
    private static void typeOktmo(String oktmo) {
        WebElement kbkField = Login.driver.findElement(By.cssSelector("input[placeholder='Введите значение ОКТМО']"));
        kbkField.sendKeys(oktmo);
    }

    /**
     * Выбрать основание платежа
     **/
    private static void selectPaymentReason(String paymentReason) {
        WebElement paymentReasonField = Login.driver.findElement(By.cssSelector("input[aria-activedescendant='react-select-6--value']"));
        paymentReasonField.sendKeys(paymentReason + ENTER);
    }

    /**
     * Выбрать налоговый период
     **/
    private static void selectTaxPeriod() {
        WebElement taxPeriodList = Login.driver.findElement(By.cssSelector("div[data-test-id='TaxPeriod__period--selectDropDown']"));
        taxPeriodList.click();
        WebElement taxPeriodOption = Login.driver.findElement(By.cssSelector("div[title='0 - не определено']"));
        taxPeriodOption.click();
    }

    /**
     * Ввести номер документа
     **/
    private static void typeTaxDocNumber(String taxDocNumber) {
        WebElement docNumberField = Login.driver.findElement(By.cssSelector("input[placeholder='Введите номер документа']"));
        docNumberField.sendKeys(taxDocNumber);
    }

    /**
     * Указать дату
     **/
    private static void selectDate() {
        WebElement dateRadioButton = Login.driver.findElement(By.cssSelector("label[for='id-4-16']"));
        dateRadioButton.click();
    }

    /**
     * Ввести УИН
     **/
    private static void typeUin(String uin) {
        WebElement uinField = Login.driver.findElement(By.cssSelector("input[placeholder='УИН']"));
        uinField.sendKeys(uin);
    }

    /**
     * Нажать на кнопку "Создать"
     **/
    private static void clickCreateButton() {
        WebElement createPaymentButton = Login.driver.findElement(By.cssSelector("button[data-analytics-label=\"Action.CREATE\"]"));
        createPaymentButton.click();
    }

    /**
     * Нажать на кнопку "Всё равно сохранить"
     **/
    private static void clickSaveButton() {
        WebElement saveButton = Login.driver.findElement(By.xpath("//button[text()='Всё равно сохранить']"));
        saveButton.click();
    }

    /**
     * Создать платёж в бюджет
     **/
    public static void createBudgetPayment(String amount, String kbk, String oktmo, String paymentReason, String taxDocNumber, String uin) {
        typeSum(amount);
        selectPayer();
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
