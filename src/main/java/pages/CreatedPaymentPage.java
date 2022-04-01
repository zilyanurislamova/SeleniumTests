package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.enums.PaymentType;
import pages.payments.BudgetPayment;
import pages.payments.CommercialPayment;
import pages.payments.PaymentBetweenAccounts;

import java.time.Duration;

public class CreatedPaymentPage {
    private final WebDriver driver;
    private final PaymentType paymentType;
    private final By paymentStatus = By.className("text-status");
    private final By signButton = By.cssSelector("button[data-analytics-label='Get SMS Code']");
    private final By codeField = By.cssSelector("input[placeholder='СМС-код']");
    private final By arrowButton = By.cssSelector("button[aria-label='Отправить']");
    private final By sendButton = By.cssSelector("button[data-test-id='Payments.Tracker.PaymentOrdersTracker__send--button']");
    private final By removeSignButton = By.xpath("//button[text()='Снять подпись']");
    private final By submitButton = By.xpath("//button[text()='Подтвердить']");
    private final By editAndCopyButtons = By.cssSelector(".actions div *");
    private final By copyOptions = By.cssSelector(".modal-body div button");
    private final By reloadButton = By.xpath("//button[text()='Попробуйте ещё раз']");
    private final By deleteButton = By.cssSelector("[data-analytics-label='Action.DELETE']");
    private final By confirmButton = By.cssSelector("[data-analytics-label='confirm']");

    public CreatedPaymentPage(WebDriver driver, PaymentType paymentType) {
        this.driver = driver;
        this.paymentType = paymentType;
        if (!driver.findElement(paymentStatus).getText().equals("Создан")) {
            throw new IllegalStateException("This is not CreatedPaymentPage");
        }
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    /**
     * Подписать
     **/
    public CreatedPaymentPage signPayment(String code) {
        clickSignButton();
        typeCode(code);
        clickArrowButton();
        return this;
    }

    /**
     * Снять подпись
     **/
    public CreatedPaymentPage removeSign() {
        driver.findElement(removeSignButton).click();
        driver.findElement(submitButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.stalenessOf(driver.findElement(removeSignButton)));
        return this;
    }

    /**
     * Отправить в банк
     **/
    public void sendPayment() {
        driver.findElement(sendButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.stalenessOf(driver.findElement(sendButton)));
    }

    /**
     * Нажать на кнопку "Изменить"
     **/
    public CommercialPayment clickEditButton() {
        driver.findElements(editAndCopyButtons).get(0).click();
        driver.findElement(reloadButton).click();
        switch (getPaymentType()) {
            case COMMERCIALPAYMENT:
                return new CommercialPayment(driver);
            case BUDGETPAYMENT:
                return new BudgetPayment(driver);
            case BETWEENACCOUNTS:
                return new PaymentBetweenAccounts(driver);
        }
        return null;
    }

    /**
     * Скопировать
     **/
    public AccountsPaymentsPage copyPayment() {
        driver.findElements(editAndCopyButtons).get(1).click();
        driver.findElement(copyOptions).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.stalenessOf(driver.findElement(editAndCopyButtons)));
        return new AccountsPaymentsPage(driver);
    }

    /**
     * Удалить
     **/
    public AccountsPaymentsPage deletePayment() {
        driver.findElements(editAndCopyButtons).get(2).click();
        driver.findElement(deleteButton).click();
        driver.findElement(confirmButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.stalenessOf(driver.findElement(editAndCopyButtons)));
        return new AccountsPaymentsPage(driver);
    }

    /**
     * Нажать кнопку "Получить СМС-код"
     **/
    private void clickSignButton() {
        driver.findElement(signButton).click();
    }

    /**
     * Ввести код
     **/
    private void typeCode(String code) {
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.presenceOfElementLocated(codeField)).sendKeys(code);
    }

    /**
     * Нажать на стрелку вправо
     **/
    private void clickArrowButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(arrowButton)).click();
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(arrowButton)));
    }
}
