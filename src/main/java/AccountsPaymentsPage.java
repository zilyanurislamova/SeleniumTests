import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class AccountsPaymentsPage {
    private final WebDriver driver;
    private final By buttonWrapper = By.className("button-wrapper");
    private final By newPaymentButton = By.cssSelector(".dropdown-new-payment.dropdown-toggle.btn.btn-success");
    private final By commercialPaymentOption = By.cssSelector("ul[role='menu'] li:first-child a");
    private final By budgetPaymentOption = By.cssSelector("ul[role='menu'] li:nth-child(3) a");
    private final By paymentBetweenAccountsOption = By.cssSelector("ul[role='menu'] li:nth-child(5) a");

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
    private CommercialPaymentPage selectPaymentType(PaymentType paymentType) {
        switch (paymentType) {
            case COMMERCIALPAYMENT:
                driver.findElement(commercialPaymentOption).click();
                return new CommercialPaymentPage(driver);
            case BUDGETPAYMENT:
                driver.findElement(budgetPaymentOption).click();
                return new BudgetPaymentPage(driver);
            case BETWEENACCOUNTS:
                driver.findElement(paymentBetweenAccountsOption).click();
                return new PaymentBetweenAccountsPage(driver);
        }
        return null;
    }

    /**
     * Открыть форму создания платежа
     **/
    public CommercialPaymentPage openPaymentCreationPage(PaymentType paymentType) {
        clickNewPaymentButton();
        return selectPaymentType(paymentType);
    }
}
