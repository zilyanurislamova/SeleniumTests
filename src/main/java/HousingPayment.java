import org.openqa.selenium.*;

public class HousingPayment {
    /** Ввести сумму **/
    public void typeSum(String amount){
        WebElement sumField = Login.driver.findElement(By.xpath("//input[@aria-label=\"Сумма списания\"]"));
        sumField.sendKeys(amount);
    }

    /** Выбрать получателя **/
    public void selectPayee(String payeePartialName){
        WebElement payeeField = Login.driver.findElement(By.xpath("//div[text()='Начните вводить наименование получателя или выберите из списка']"));
        payeeField.click();
        WebElement payeeQuery = Login.driver.findElement(By.xpath("//input[@placeholder='Начните вводить наименование получателя или выберите из списка']"));
        payeeQuery.sendKeys(payeePartialName);
        WebElement payeeOption = Login.driver.findElement(By.cssSelector("div[role='listbox'] div div[title*='ЖКУ РАН']"));
        payeeOption.click();
    }

    /** Нажать кнопку "Создать" **/
    public void clickCreateButton(){
        WebElement createPaymentButton = Login.driver.findElement(By.xpath("//button[@data-analytics-label=\"action.create_payment\"]"));
        createPaymentButton.click();
    }

    /** Создать плтаёж ЖКУ **/
    public static void createHousingPayment(HousingPayment paymentForm, String amount, String payeePartialName){
        paymentForm.typeSum(amount);
        paymentForm.selectPayee(payeePartialName);
        paymentForm.clickCreateButton();
    }
}
