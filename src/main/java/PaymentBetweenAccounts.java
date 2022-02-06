import org.openqa.selenium.*;

import static org.openqa.selenium.Keys.DOWN;
import static org.openqa.selenium.Keys.ENTER;

public class PaymentBetweenAccounts extends PaymentCreation {
    private WebDriver driver;
    /**
     * Выбрать счёт списания
     **/
    @Override
    public void selectPayer(String payeePartialName) {
        WebElement payerField = driver.findElement(By.cssSelector("input[aria-activedescendant=\"react-select-4--value\"]"));
        payerField.sendKeys(ENTER);
    }

    /**
     * Выбрать счёт зачисления
     **/
    @Override
    public void selectPayee(String payerPartialName) {
        WebElement payeeField = driver.findElement(By.cssSelector("input[aria-activedescendant=\"react-select-5--value\"]"));
        payeeField.sendKeys(DOWN, ENTER);
    }

    /**
     * Ввести сумму
     **/
    @Override
    public void typeSum(String amount) {
        WebElement sumField = driver.findElement(By.cssSelector("input[placeholder=\"0,00\"]"));
        sumField.sendKeys(amount);
    }

    /**
     * Создать платёж между своими счетами
     **/
    public void createPaymentBetweenAccounts(String amount) {
        selectPayer("");
        selectPayee("");
        typeSum(amount);
        clickCreateButton();
        clickSaveButton();
    }
}
