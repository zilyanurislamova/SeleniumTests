import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.Keys.ENTER;


public class PaymentCreation {
    /**
     * Открыть раздел "Счета и платежи"
     **/
    private void openAccountsPaymentsPage() {
        WebElement accountsPaymentsOption = Login.driver.findElement(By.cssSelector("button[aria-label=\"Счета и платежи\"]"));
        accountsPaymentsOption.click();
    }

    /**
     * Нажать на кнопку "Новый платёж"
     **/
    private void clickNewPaymentButton() {
        Login.driver.findElement(By.className("button-wrapper")).click();
        WebElement newPaymentButton = Login.driver.findElement(By.cssSelector(".dropdown-new-payment.dropdown-toggle.btn.btn-success"));
        newPaymentButton.click();
    }

    /**
     * Выбрать тип платежа
     **/
    private void selectPaymentType(PaymentTypes paymentType) {
        switch (paymentType) {
            case COMMERCIALPAYMENT:
                WebElement commercialPaymentOption = Login.driver.findElement(By.cssSelector("ul[role='menu'] li:first-child a"));
                commercialPaymentOption.click();
                break;
            case BUDGETPAYMENT:
                WebElement budgetPaymentOption = Login.driver.findElement(By.cssSelector("ul[role='menu'] li:nth-child(3) a"));
                budgetPaymentOption.click();
                break;
            case BETWEENACCOUNTS:
                WebElement paymentBetweenAccountsOption = Login.driver.findElement(By.cssSelector("ul[role='menu'] li:nth-child(5) a"));
                paymentBetweenAccountsOption.click();
                break;
        }
    }

    /**
     * Открыть форму создания платежа
     **/
    public void openPaymentCreationForm(PaymentTypes paymentType) {
        openAccountsPaymentsPage();
        clickNewPaymentButton();
        selectPaymentType(paymentType);
    }

    /**
     * Ввести сумму
     **/
    public void typeSum(String amount) {
        WebElement sumField = Login.driver.findElement(By.cssSelector("input[name=\"amount\"]"));
        sumField.sendKeys(amount);
    }

    /**
     * Выбрать плательщика
     **/
    public void selectPayer(String payerPartialName) {
        WebElement payerField = Login.driver.findElement(By.cssSelector("input[aria-activedescendant=\"react-select-5--value\"]"));
        payerField.sendKeys(payerPartialName + ENTER);
    }

    /**
     * Выбрать получателя
     **/
    public void selectPayee(String payeePartialName) {
        WebElement payeeField = Login.driver.findElement(By.cssSelector("input[placeholder='Начните вводить наименование получателя или выберите из списка']"));
        payeeField.sendKeys(payeePartialName);
        WebElement payeeOption = Login.driver.findElement(By.cssSelector("div[role='listbox'] div div[title*='Контрагент 0']"));
        payeeOption.click();
    }

    /**
     * Нажать на кнопку "Создать"
     **/
    public void clickCreateButton() {
        WebElement createPaymentButton = Login.driver.findElement(By.cssSelector("button[data-analytics-label=\"Action.CREATE\"]"));
        createPaymentButton.click();
    }

    /**
     * Нажать на кнопку "Всё равно сохранить"
     **/
    public void clickSaveButton() {
        WebElement saveButton = Login.driver.findElement(By.xpath("//button[text()='Всё равно сохранить']"));
        saveButton.click();
    }
}
