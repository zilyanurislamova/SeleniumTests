import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.Keys.ENTER;

public class CommercialPaymentPage {
    private final WebDriver driver;
    private final By sumField = By.cssSelector("input[name=\"amount\"]");
    private final By payerField = By.cssSelector("input[aria-activedescendant=\"react-select-5--value\"]");
    private final By payeeField = By.cssSelector("input[placeholder='Начните вводить наименование получателя или выберите из списка']");
    private final By payeeOption = By.cssSelector("div[role='listbox'] div div[title*='Контрагент 0']");
    private final By createPayeeButton = By.xpath("//button[text()='Создать нового']");
    private final By createPaymentButton = By.cssSelector("button[data-analytics-label=\"Action.CREATE\"]");
    private final By saveButton = By.xpath("//button[text()='Всё равно сохранить']");

    public CommercialPaymentPage(WebDriver driver) {
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
     * Нажать на кнопку "Создать нового"
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
     * Нажать на кнопку "Всё равно сохранить"
     **/
    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    /**
     * Создать платёж контрагенту
     **/
    public CreatedPaymentPage createCommercialPayment(String amount, String payerPartialName, String payeePartialName) {
        typeSum(amount);
        selectPayer(payerPartialName);
        selectPayee(payeePartialName);
        clickCreateButton();
        clickSaveButton();
        return new CreatedPaymentPage(driver);
    }
}
