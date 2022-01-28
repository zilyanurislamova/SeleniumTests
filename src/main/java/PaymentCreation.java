import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.*;


public class PaymentCreation {
    /** Открыть раздел "Счета и платежи" **/
    public void openAccountsPaymentsPage(){
        WebElement accountsPaymentsOption = Login.driver.findElement(By.cssSelector("button[aria-label=\"Счета и платежи\"]"));
        accountsPaymentsOption.click();
    }

    /** Нажать на кнопку "Новый платёж" **/
    public void clickNewPaymentButton(){
        new WebDriverWait(Login.driver, Duration.ofSeconds(5)).
              until(ExpectedConditions.invisibilityOfElementLocated(By.className("globalSpinnerWidget__u-EKzw")));
        WebElement newPaymentButton = Login.driver.findElement(By.cssSelector(".dropdown-new-payment.dropdown-toggle.btn.btn-success"));
        newPaymentButton.click();
    }

    /** Выбрать тип платежа **/
    public void selectPaymentType(PaymentTypes paymentType){
        switch (paymentType) {
            case COMMERCIALPAYMENT:
                WebElement commercialPaymentOption = Login.driver.findElement(By.cssSelector("ul[role='menu'] li:first-child a"));
                commercialPaymentOption.click();
                break;
            case BUDGETPAYMENT:
                WebElement budgetPaymentOption = Login.driver.findElement(By.cssSelector("ul[role='menu'] li:nth-child(2) a"));
                budgetPaymentOption.click();
                break;
            case HOUSING:
                WebElement housingPaymentOption = Login.driver.findElement(By.cssSelector("ul[role='menu'] li:nth-child(3) a"));
                housingPaymentOption.click();
                break;
            case BETWEENACCOUNTS:
                WebElement paymentBetweenAccountsOption = Login.driver.findElement(By.cssSelector("ul[role='menu'] li:nth-child(4) a"));
                paymentBetweenAccountsOption.click();
                break;
        }
    }
}
