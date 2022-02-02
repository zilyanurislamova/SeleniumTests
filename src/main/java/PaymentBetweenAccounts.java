import org.openqa.selenium.*;

import static org.openqa.selenium.Keys.DOWN;
import static org.openqa.selenium.Keys.ENTER;

public class PaymentBetweenAccounts {
    /** Выбрать счёт списания **/
    public void selectPayer(){
        WebElement payerField = Login.driver.findElement(By.cssSelector("input[aria-activedescendant=\"react-select-4--value\"]"));
        payerField.sendKeys(ENTER);
    }

    /** Выбрать счёт зачисления **/
    public void selectPayee(){
        WebElement payeeField = Login.driver.findElement(By.cssSelector("input[aria-activedescendant=\"react-select-5--value\"]"));
        payeeField.sendKeys(DOWN, ENTER);
    }

    /** Ввести сумму **/
    public void typeSum(String amount){
        WebElement sumField = Login.driver.findElement(By.cssSelector("input[placeholder=\"0,00\"]"));
        sumField.sendKeys(amount);
    }

    /** Нажать на кнопку "Создать" **/
    public void clickCreateButton(){
        WebElement createPaymentButton = Login.driver.findElement(By.cssSelector("button[data-analytics-label=\"Action.CREATE\"]"));
        createPaymentButton.click();
    }

    /** Нажать на кнопку "Всё равно сохранить" **/
    public void clickSaveButton(){
        WebElement saveButton = Login.driver.findElement(By.xpath("//button[text()='Всё равно сохранить']"));
        saveButton.click();
    }

    /** Создать платёж между своими счетами **/
    public static void createPaymentBetweenAccounts(PaymentBetweenAccounts paymentForm, String amount){
        paymentForm.selectPayer();
        paymentForm.selectPayee();
        paymentForm.typeSum(amount);
        paymentForm.clickCreateButton();
        paymentForm.clickSaveButton();
    }
}
