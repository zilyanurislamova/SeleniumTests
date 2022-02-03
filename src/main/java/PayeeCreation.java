import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PayeeCreation {
    /**
     * Нажать на кнопку "Создать нового"
     **/
    private static void clickCreateButton() {
        WebElement createPayeeButton = Login.driver.findElement(By.xpath("//button[text()='Создать нового']"));
        createPayeeButton.click();
    }

    /**
     * Ввести наименование контрагента
     **/
    private static void typePayeeName(String payeeName) {
        WebElement payeeNameField = Login.driver.findElement(By.cssSelector("input[placeholder='Наименование контрагента']"));
        payeeNameField.sendKeys(payeeName);
    }

    /**
     * Ввести ИНН
     **/
    private static void typeInn(String inn) {
        WebElement innField = Login.driver.findElement(By.cssSelector("input[placeholder='Введите ИНН или КИО']"));
        innField.sendKeys(inn);
    }

    /**
     * Ввести КПП
     **/
    private static void typeKpp(String kpp) {
        WebElement kppField = Login.driver.findElement(By.cssSelector("input[placeholder='Введите КПП']"));
        kppField.sendKeys(kpp);
    }

    /**
     * Ввести номер счёта
     **/
    private static void typeAccountNumber(String accountNumber) {
        WebElement accountNumberField = Login.driver.findElement(By.cssSelector("input[name='accountNumber']"));
        accountNumberField.sendKeys(accountNumber);
    }

    /**
     * Ввести БИК
     **/
    private static void typeBic(String bic) {
        WebElement bicField = Login.driver.findElement(By.cssSelector("input[placeholder='Введите несколько цифр']"));
        bicField.sendKeys(bic);
    }

    /**
     * Нажать на кнопку "Добавить"
     **/
    private static void clickSubmitButton() {
        WebElement submitButton = Login.driver.findElement(By.xpath("//button[text()='Добавить']"));
        new WebDriverWait(Login.driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    /**
     * Создать получателя
     **/
    public static void createPayee(String payeeName, String inn, String kpp, String accountNumber, String bic) {
        clickCreateButton();
        typePayeeName(payeeName);
        typeInn(inn);
        typeKpp(kpp);
        typeAccountNumber(accountNumber);
        typeBic(bic);
        clickSubmitButton();
    }
}
