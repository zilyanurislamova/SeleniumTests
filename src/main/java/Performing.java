import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Performing {
    /** Нажать на кнопку "Отправить в банк" **/
    public static void clickSendButton(){
        WebElement sendButton = Login.driver.findElement(By.cssSelector("button[data-test-id='Payments.Tracker.PaymentOrdersTracker__send--button']"));
        sendButton.click();
    }
}
