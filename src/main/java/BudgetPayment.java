import org.openqa.selenium.*;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class BudgetPayment {
    /** Ввести сумму **/
    public void typeSum(String amount){
        WebElement sumField = Login.driver.findElement(By.cssSelector("input[aria-label='Сумма списания']"));
        sumField.sendKeys(amount);
    }

    /** Выбрать получателя **/
    public void selectPayee(String payeePartialName){
        WebElement payeeField = Login.driver.findElement(By.xpath("//div[text()='Начните вводить наименование получателя или выберите из списка']"));
        payeeField.click();
        WebElement payeeQuery = Login.driver.findElement(By.xpath("//input[@placeholder='Начните вводить наименование получателя или выберите из списка']"));
        payeeQuery.sendKeys(payeePartialName);
        WebElement payeeOption = Login.driver.findElement(By.cssSelector("div[role='listbox'] div div[title*='АОЗТ \"БЮДЖЕТ\"']"));
        payeeOption.click();
    }

    /** Выбрать статус плательщика **/
    public void selectPayerStatus(){
        WebElement payerStatusField = Login.driver.findElement(with(By.xpath("//div[text()='Выберите значение']")).above(By.cssSelector("input[aria-label='КБК']")));
        payerStatusField.click();
        WebElement payerStatusOption = Login.driver.findElement(By.cssSelector("div[role='listbox'] div[title*='тестналогоплательщик']"));
        payerStatusOption.click();
    }

    /** Ввести КБК **/
    public void typeKbk(String kbk){
        WebElement kbkField = Login.driver.findElement(By.cssSelector("input[aria-label='КБК']"));
        kbkField.sendKeys(kbk);
    }

    /** Выбрать основание платежа **/
    public void selectPaymentReason(){
        WebElement paymentReasonField = Login.driver.findElement(By.cssSelector("input[aria-label='Основание платежа']"));
        paymentReasonField.click();
        WebElement paymentReasonOption = Login.driver.findElement(By.cssSelector("div[role='listbox'] div[title*='не определен']"));
        paymentReasonOption.click();
    }

    /** Ввести номер документа **/
    public void typeDocNumber(String docNumber){
        WebElement docNumberField = Login.driver.findElement(By.cssSelector("input[aria-label='Номер документа']"));
        docNumberField.sendKeys(docNumber);
    }

    /** Указать дату **/
    public void selectDate(){
        WebElement dateRadioButton = Login.driver.findElement(By.cssSelector("div[aria-label='Дата'] label:first-child"));
        dateRadioButton.click();
    }

    /** Ввести УИН **/
    public void typeUin(String uin){
        WebElement uinField = Login.driver.findElement(By.cssSelector("input[aria-label='Необязательно']"));
        uinField.sendKeys(uin);
    }

    /** Нажать кнопку "Создать" **/
    public void clickCreateButton(){
        WebElement createPaymentButton = Login.driver.findElement(By.xpath("//button[@data-analytics-label=\"action.create_payment\"]"));
        createPaymentButton.click();
    }

    /** Создать платёж в бюджет **/
    public static void createBudgetPayment(BudgetPayment paymentForm, String amount, String payeePartialName, String kbk, String docNumber, String uin){
        paymentForm.typeSum(amount);
        paymentForm.selectPayee(payeePartialName);
        //paymentForm.selectPayerStatus();
        paymentForm.typeKbk(kbk);
        paymentForm.selectPaymentReason();
        paymentForm.typeDocNumber(docNumber);
        paymentForm.selectDate();
        paymentForm.typeUin(uin);
        paymentForm.clickCreateButton();
    }
}
