import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.Keys.ENTER;

public class CommercialPayment {
    private WebDriver driver;
    private By sumField = By.cssSelector("input[name=\"amount\"]");
    private By payerField = By.cssSelector("input[aria-activedescendant=\"react-select-5--value\"]");
    private By payeeField = By.cssSelector("input[placeholder='Начните вводить наименование получателя или выберите из списка']");
    private By payeeOption = By.cssSelector("div[role='listbox'] div div[title*='Контрагент 0']");
    private By createPaymentButton = By.cssSelector("button[data-analytics-label=\"Action.CREATE\"]");
    private By saveButton = By.xpath("//button[text()='Всё равно сохранить']");

    public CommercialPayment(WebDriver driver) {
        this.driver = driver;
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
    public void selectPayer(String payerPartialName) {
        driver.findElement(payerField).sendKeys(payerPartialName + ENTER);
    }

    /**
     * Выбрать получателя
     **/
    public void selectPayee(String payeePartialName) {
        driver.findElement(payeeField).sendKeys(payeePartialName);
        driver.findElement(payeeOption).click();
    }

    /**
     * Нажать на кнопку "Создать"
     **/
    public void clickCreateButton() {
        driver.findElement(createPaymentButton).click();
    }

    /**
     * Нажать на кнопку "Всё равно сохранить"
     **/
    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    /**
     * Создать платёж контрагенту
     **/
    public CreatedPayment createCommercialPayment(String amount, String payerPartialName, String payeePartialName) {
        typeSum(amount);
        selectPayer(payerPartialName);
        selectPayee(payeePartialName);
        clickCreateButton();
        clickSaveButton();
        return new CreatedPayment();
    }
}
