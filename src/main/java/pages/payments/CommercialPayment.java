package pages.payments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CreatedPaymentPage;
import pages.enums.PaymentType;
import pages.payments.payee.Payee;

import static org.openqa.selenium.Keys.ENTER;

public class CommercialPayment {
    protected WebDriver driver;
    protected By pageHeader = By.tagName("h1");
    protected By payerField = By.cssSelector("input[aria-activedescendant]");
    private final By sumField = By.cssSelector("[placeholder='0,00']");
    private final By payeeField = By.name("payeeName");
    private final By payeeOption = By.cssSelector("div[role='option']");
    private final By createPayeeButton = By.xpath("//button[text()='Создать нового']");
    private final By createPaymentButton = By.cssSelector("[data-analytics-label='Action.CREATE']");
    private final By submitSaveButton = By.xpath("//button[text()='Всё равно сохранить']");
    private final By saveButton = By.cssSelector("[data-analytics-label='Action.SAVE']");
    private final PaymentType paymentType = PaymentType.COMMERCIALPAYMENT;

    public CommercialPayment(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Создать платёж контрагенту
     **/
    public CreatedPaymentPage createPayment(String amount, String payerName, String payeeName) {
        typeSum(amount);
        selectPayer(payerName);
        selectPayee(payeeName);
        clickCreateButton();
        submitSave();
        return new CreatedPaymentPage(driver, paymentType);
    }

    /**
     * Редактировать платёжное поручение
     **/
    public CreatedPaymentPage editPayment(String amount) {
        driver.findElement(sumField).clear();
        typeSum(amount);
        clickSaveButton();
        submitSave();
        return new CreatedPaymentPage(driver, paymentType);
    }

    /**
     * Ввести сумму
     **/
    public void typeSum(String amount) {
        driver.findElement(sumField).sendKeys(amount);
    }

    /**
     * Выбрать плательщика
     **/
    public void selectPayer(String payerName) {
        driver.findElement(payerField).sendKeys(payerName + ENTER);
    }

    /**
     * Выбрать получателя
     **/
    public void selectPayee(String payeeName) {
        driver.findElement(payeeField).sendKeys(payeeName);
        driver.findElement(payeeOption).click();
    }

    /**
     * Нажать на кнопку "Создать нового" (контрагента)
     **/
    public Payee clickCreatePayeeButton() {
        driver.findElement(createPayeeButton).click();
        return new Payee(driver);
    }

    /**
     * Нажать на кнопку "Создать"
     **/
    public void clickCreateButton() {
        driver.findElement(createPaymentButton).click();
    }

    /**
     * Подтвердить сохранение
     **/
    public void submitSave() {
        driver.findElement(submitSaveButton).click();
    }

    /**
     * Нажать на кнопку "Сохранить"
     **/
    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }
}

