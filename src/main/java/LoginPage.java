import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private By demoLink = By.cssSelector("a[href='https://sbi.sberbank.ru:9444/ic/dcb/login.html?demoMode&version=3.0']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Войти в демо-режим
     **/
    public MainPage login() {
        driver.findElement(demoLink).click();
        return new MainPage(driver);
    }
}
