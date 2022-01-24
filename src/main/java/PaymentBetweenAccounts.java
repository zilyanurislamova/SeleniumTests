import org.openqa.selenium.*;

public class PaymentBetweenAccounts {
    /** Ввести сумму **/
    public void typeSum(String amount){
        WebElement sumField = Login.driver.findElement(By.xpath("//input[@placeholder=\"0,00\"]"));
        sumField.sendKeys(amount);
    }

    /** Выбрать получателя **/
    public void selectPayee(){
        WebElement payeeField = Login.driver.findElement(By.xpath("//div[text()='Начните вводить наименование получателя или выберите из списка']"));
        payeeField.click();
        WebElement payeeOption = Login.driver.findElement(By.cssSelector("div[role='listbox'] div[title*='38000248069']"));
        payeeOption.click();
    }

    /** Нажать кнопку "Создать" **/
    public void clickCreateButton(){
        WebElement createPaymentButton = Login.driver.findElement(By.xpath("//button[@data-analytics-label=\"action.create_payment\"]"));
        createPaymentButton.click();
    }

    /** Создать платёж между своими счетами **/
    public static void createPaymentBetweenAccounts(PaymentBetweenAccounts paymentForm, String amount){
        paymentForm.typeSum(amount);
        paymentForm.selectPayee();
        paymentForm.clickCreateButton();
    }
}
