import org.openqa.selenium.WebDriver;

public class CommercialPayment extends PaymentCreation {
    private WebDriver driver;
    /**
     * Создать платёж контрагенту
     **/
    public void createCommercialPayment(String amount, String payerPartialName, String payeePartialName) {
        typeSum(amount);
        selectPayer(payerPartialName);
        selectPayee(payeePartialName);
        clickCreateButton();
        clickSaveButton();
    }
}
