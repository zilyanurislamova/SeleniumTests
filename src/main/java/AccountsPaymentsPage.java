import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.Keys.ENTER;


public class AccountsPaymentsPage {
    private WebDriver driver;
    private By buttonWrapper = By.className("button-wrapper");
    private By newPaymentButton = By.cssSelector(".dropdown-new-payment.dropdown-toggle.btn.btn-success");
    private By commercialPaymentOption = By.cssSelector("ul[role='menu'] li:first-child a");
    private By budgetPaymentOption = By.cssSelector("ul[role='menu'] li:nth-child(3) a");
    private By paymentBetweenAccountsOption = By.cssSelector("ul[role='menu'] li:nth-child(5) a");

    public AccountsPaymentsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Нажать на кнопку "Новый платёж"
     **/
    private void clickNewPaymentButton() {
        driver.findElement(buttonWrapper).click();
        driver.findElement(newPaymentButton).click();
    }

    /**
     * Выбрать тип платежа
     **/
    private void selectPaymentType(PaymentTypes paymentType) {
        switch (paymentType) {
            case COMMERCIALPAYMENT:
                driver.findElement(commercialPaymentOption).click();
                break;
            case BUDGETPAYMENT:
                driver.findElement(budgetPaymentOption).click();
                break;
            case BETWEENACCOUNTS:
                driver.findElement(paymentBetweenAccountsOption).click();
                break;
        }
    }

    /**
     * Открыть форму создания платежа
     **/
    public void openPaymentCreationForm(PaymentTypes paymentType) {
        clickNewPaymentButton();
        selectPaymentType(paymentType);
    }

    /**
     * Ввести сумму
     **/
    public void typeSum(String amount) {
        WebElement sumField = driver.findElement(By.cssSelector("input[name=\"amount\"]"));
        sumField.sendKeys(amount);
    }

    /**
     * Выбрать плательщика
     **/
    public void selectPayer(String payerPartialName) {
        WebElement payerField = driver.findElement(By.cssSelector("input[aria-activedescendant=\"react-select-5--value\"]"));
        payerField.sendKeys(payerPartialName + ENTER);
    }

    /**
     * Выбрать получателя
     **/
    public void selectPayee(String payeePartialName) {
        WebElement payeeField = driver.findElement(By.cssSelector("input[placeholder='Начните вводить наименование получателя или выберите из списка']"));
        payeeField.sendKeys(payeePartialName);
        WebElement payeeOption = driver.findElement(By.cssSelector("div[role='listbox'] div div[title*='Контрагент 0']"));
        payeeOption.click();
    }

    /**
     * Нажать на кнопку "Создать"
     **/
    public void clickCreateButton() {
        WebElement createPaymentButton = driver.findElement(By.cssSelector("button[data-analytics-label=\"Action.CREATE\"]"));
        createPaymentButton.click();
    }

    /**
     * Нажать на кнопку "Всё равно сохранить"
     **/
    public void clickSaveButton() {
        WebElement saveButton = driver.findElement(By.xpath("//button[text()='Всё равно сохранить']"));
        saveButton.click();
    }
}
