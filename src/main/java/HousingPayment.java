import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.Keys.ENTER;

public class HousingPayment {
    /** Ввести сумму **/
    public void typeSum(String amount){
        WebElement sumField = Login.driver.findElement(By.cssSelector("input[name=\"amount\"]"));
        sumField.sendKeys(amount);
    }

    /** Выбрать плательщика **/
    public void selectPayer(){
        WebElement payerField = Login.driver.findElement(By.cssSelector("input[aria-activedescendant=\"react-select-5--value\"]"));
        payerField.sendKeys(ENTER);
    }

    /** Создать получателя **/
    public void createPayee(){
        WebElement createPayeeButton = Login.driver.findElement(By.xpath("//button[text()='Создать нового']"));
        createPayeeButton.click();
        WebElement payeeNameField = Login.driver.findElement(By.cssSelector("input[placeholder='Наименование контрагента']"));
        payeeNameField.sendKeys("Бюджет");
        WebElement innField = Login.driver.findElement(By.cssSelector("input[placeholder='Введите ИНН или КИО']"));
        innField.sendKeys("7806000792");
        WebElement kppField = Login.driver.findElement(By.cssSelector("input[placeholder='Введите КПП']"));
        kppField.sendKeys("780601001");
        WebElement accountNumberField = Login.driver.findElement(By.cssSelector("input[name='accountNumber']"));
        accountNumberField.sendKeys("40101810523456789101");
        WebElement bicField = Login.driver.findElement(By.cssSelector("input[placeholder='Введите несколько цифр']"));
        bicField.sendKeys("041946000");
        WebElement submitButton = Login.driver.findElement(By.xpath("//button[text()='Добавить']"));
        new WebDriverWait(Login.driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
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

    /** Создать плтаёж ЖКУ **/
    public static void createHousingPayment(HousingPayment paymentForm, String amount){
        paymentForm.typeSum(amount);
        paymentForm.selectPayer();
        paymentForm.createPayee();
        paymentForm.clickCreateButton();
        paymentForm.clickSaveButton();
    }
}
