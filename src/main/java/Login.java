import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import java.time.*;
import java.util.*;

public class Login {
    static WebDriver driver = new ChromeDriver();

    /** Открыть страницу **/
    public void openPage(String url){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
    }

    /** Ввести Логин **/
    public void typeLogin(String login){
        WebElement loginField = driver.findElement(By.cssSelector("#inputLogin"));
        loginField.sendKeys(login);
    }

    /** Ввести Пароль **/
    public void typePassword(String password){
        WebElement passwordField = driver.findElement(By.cssSelector("#inputPassword"));
        passwordField.sendKeys(password);
    }

    /** Нажать кнопку "Войти" **/
    public void clickEnterButton(){
        driver.findElement(By.cssSelector(".col-xs-6.text-right.login-action")).click();
    }

    /** Ввести смс-код **/
    public void typeCode(String code){
        List<WebElement> numbers = driver.findElements(By.cssSelector(".sms-code-input-wrapper input"));
        for (WebElement number : numbers){
            number.sendKeys(code);
        }
    }

    /** Залогиниться **/
    public static void login(Login loginPage, String url, String login, String password, String code){
        loginPage.openPage(url);
        loginPage.typeLogin(login);
        loginPage.typePassword(password);
        loginPage.clickEnterButton();
        loginPage.typeCode(code);
    }
}
