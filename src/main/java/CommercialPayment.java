import net.bytebuddy.asm.Advice;
import org.openqa.selenium.*;

import static org.openqa.selenium.Keys.ENTER;

public class CommercialPayment {
    /** Ввести сумму **/
    public void typeSum(String amount){
        WebElement sumField = Login.driver.findElement(By.cssSelector("input[name=\"amount\"]"));
        sumField.sendKeys(amount);
    }

    /** Выбрать плательщика **/
    public void selectPayer(String payerPartialName){
        WebElement payerField = Login.driver.findElement(By.cssSelector("input[aria-activedescendant=\"react-select-5--value\"]"));
        payerField.sendKeys(payerPartialName + ENTER);
    }

    /** Выбрать получателя **/
    public void selectPayee(String payeePartialName){
        WebElement payeeField = Login.driver.findElement(By.cssSelector("input[placeholder='Начните вводить наименование получателя или выберите из списка']"));
        payeeField.sendKeys(payeePartialName);
        WebElement payeeOption = Login.driver.findElement(By.cssSelector("div[role='listbox'] div div[title*='Контрагент 0']"));
        payeeOption.click();
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

    /** Создать платёж контрагенту **/
    public static void createCommercialPayment(CommercialPayment paymentForm, String amount, String payerPartialName, String payeePartialName){
        paymentForm.typeSum(amount);
        paymentForm.selectPayer(payerPartialName);
        paymentForm.selectPayee(payeePartialName);
        paymentForm.clickCreateButton();
        paymentForm.clickSaveButton();
    }
}
