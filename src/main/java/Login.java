import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Login {
    static WebDriver driver = new ChromeDriver();

    /** Открыть страницу **/
    public void openPage(String url){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
    }

    /** Нажать на ссылку "Войти в демо-режим" **/
    public void clickDemoLink(){
        driver.findElement(By.cssSelector("a[href='https://sbi.sberbank.ru:9444/ic/dcb/login.html?demoMode&version=3.0']")).click();
    }

    /** Залогиниться **/
    public static void login(Login loginPage, String url){
        loginPage.openPage(url);
        loginPage.clickDemoLink();
    }
}
