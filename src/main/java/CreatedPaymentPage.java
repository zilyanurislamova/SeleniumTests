import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreatedPaymentPage {
    private final WebDriver driver;
    private final By paymentStatus = By.cssSelector(".text-status");
    private final By signButton = By.cssSelector("button[data-analytics-label='Get SMS Code']");
    private final By codeField = By.cssSelector("input[placeholder='СМС-код']");
    private final By arrowButton = By.cssSelector("button[aria-label='Отправить']");
    private final By sendButton = By.cssSelector("button[data-test-id='Payments.Tracker.PaymentOrdersTracker__send--button']");

    public CreatedPaymentPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.findElement(paymentStatus).getText().equals("Создан")) {
            throw new IllegalStateException("This is not CreatedPaymentPage");
        }
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
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.elementToBeClickable(arrowButton)).click();
    }

    /**
     * Подписать платёжку
     **/
    public CreatedPaymentPage signPayment(String code) {
        clickSignButton();
        typeCode(code);
        clickArrowButton();
        return this;
    }

    /**
     * Нажать на кнопку "Отправить в банк"
     **/
    public void clickSendButton() {
        driver.findElement(sendButton).click();
    }
}
