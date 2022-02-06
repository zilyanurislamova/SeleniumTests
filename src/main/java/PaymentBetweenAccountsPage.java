import org.openqa.selenium.*;

import static org.openqa.selenium.Keys.DOWN;
import static org.openqa.selenium.Keys.ENTER;

public class PaymentBetweenAccountsPage extends CommercialPaymentPage {
    private final WebDriver driver;
    private final By payerField = By.cssSelector("input[aria-activedescendant=\"react-select-4--value\"]");
    private final By payeeField = By.cssSelector("input[aria-activedescendant=\"react-select-5--value\"]");
    private final By sumField = By.cssSelector("input[placeholder=\"0,00\"]");

    public PaymentBetweenAccountsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Выбрать счёт списания
     **/
    @Override
    public void selectPayer(String payeePartialName) {
        driver.findElement(payerField).sendKeys(ENTER);
    }

    /**
     * Выбрать счёт зачисления
     **/
    @Override
    public void selectPayee(String payerPartialName) {
        driver.findElement(payeeField).sendKeys(DOWN, ENTER);
    }

    /**
     * Ввести сумму
     **/
    @Override
    public void typeSum(String amount) {
        driver.findElement(sumField).sendKeys(amount);
    }

    /**
     * Создать платёж между своими счетами
     **/
    public CreatedPaymentPage createPaymentBetweenAccounts(String amount) {
        selectPayer("");
        selectPayee("");
        typeSum(amount);
        clickCreateButton();
        clickSaveButton();
        return new CreatedPaymentPage(driver);
    }
}
