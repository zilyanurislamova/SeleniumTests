package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;
    private final By personName = By.className("person-name");
    private final By accountsPaymentsOption = By.cssSelector("[aria-label='Счета и платежи']");
    private final By logoutButton = By.cssSelector("[title='Выход']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPersonName() {
        return driver.findElement(personName).getText();
    }

    /**
     * Открыть раздел "Счета и платежи"
     **/
    public AccountsPaymentsPage openAccountsPaymentsPage() {
        driver.findElement(accountsPaymentsOption).click();
        return new AccountsPaymentsPage(driver);
    }

    /**
     * Выход
     **/
    public LoginPage logout() {
        driver.findElement(logoutButton).click();
        return new LoginPage(driver);
    }
}
