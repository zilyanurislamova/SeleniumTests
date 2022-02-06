import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class AccountsPaymentsPage {
    private WebDriver driver;
    private By buttonWrapper = By.className("button-wrapper");
    private By newPaymentButton = By.cssSelector(".dropdown-new-payment.dropdown-toggle.btn.btn-success");
    private By commercialPaymentOption = By.cssSelector("ul[role='menu'] li:first-child a");
    private By budgetPaymentOption = By.cssSelector("ul[role='menu'] li:nth-child(3) a");
    private By paymentBetweenAccountsOption = By.cssSelector("ul[role='menu'] li:nth-child(5) a");

    public AccountsPaymentsPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Нажать на кнопку "Новый платёж"
     **/
    private void clickNewPaymentButton() {
        driver.findElement(buttonWrapper).click();
        driver.findElement(newPaymentButton).click();
    }

    /**
     * Выбрать тип платежа
     **/
    private CommercialPayment selectPaymentType(PaymentTypes paymentType) {
        switch (paymentType) {
            case COMMERCIALPAYMENT:
                driver.findElement(commercialPaymentOption).click();
                return new CommercialPayment(driver);
            case BUDGETPAYMENT:
                driver.findElement(budgetPaymentOption).click();
                return new BudgetPayment(driver);
            case BETWEENACCOUNTS:
                driver.findElement(paymentBetweenAccountsOption).click();
                return new PaymentBetweenAccounts(driver);
        }
        return null;
    }

    /**
     * Открыть форму создания платежа
     **/
    public CommercialPayment openPaymentCreationForm(PaymentTypes paymentType) {
        clickNewPaymentButton();
        return selectPaymentType(paymentType);
    }
}
