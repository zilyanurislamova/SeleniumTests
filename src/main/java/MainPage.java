import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;
    private By demoAccountName = By.cssSelector("div[title='Иванов Иван Иванович']");
    private By accountsPaymentsOption = By.cssSelector("button[aria-label=\"Счета и платежи\"]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getDemoAccountName() {
        return driver.findElement(demoAccountName).getText();
    }

    /**
     * Открыть раздел "Счета и платежи"
     **/
    public AccountsPaymentsPage openAccountsPaymentsPage() {
        driver.findElement(accountsPaymentsOption).click();
        return new AccountsPaymentsPage(driver);
    }
}
