import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Payee {
    private final WebDriver driver;
    private final By payeeNameField = By.cssSelector("input[placeholder='Наименование контрагента']");
    private final By innField = By.cssSelector("input[placeholder='Введите ИНН или КИО']");
    private final By kppField = By.cssSelector("input[placeholder='Введите КПП']");
    private final By accountNumberField = By.cssSelector("input[name='accountNumber']");
    private final By bicField = By.cssSelector("input[placeholder='Введите несколько цифр']");
    private final By submitButton = By.xpath("//button[text()='Добавить']");

    public Payee(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Ввести наименование контрагента
     **/
    private void typePayeeName(String payeeName) {
        driver.findElement(payeeNameField).sendKeys(payeeName);
    }

    /**
     * Ввести ИНН
     **/
    private void typeInn(String inn) {
        driver.findElement(innField).sendKeys(inn);
    }

    /**
     * Ввести КПП
     **/
    private void typeKpp(String kpp) {
        driver.findElement(kppField).sendKeys(kpp);
    }

    /**
     * Ввести номер счёта
     **/
    private void typeAccountNumber(String accountNumber) {
        driver.findElement(accountNumberField).sendKeys(accountNumber);
    }

    /**
     * Ввести БИК
     **/
    private void typeBic(String bic) {
        driver.findElement(bicField).sendKeys(bic);
    }

    /**
     * Нажать на кнопку "Добавить"
     **/
    private void clickSubmitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    /**
     * Создать получателя
     **/
    public void createPayee(String payeeName, String inn, String kpp, String accountNumber, String bic) {
        typePayeeName(payeeName);
        typeInn(inn);
        typeKpp(kpp);
        typeAccountNumber(accountNumber);
        typeBic(bic);
        clickSubmitButton();
    }
}
