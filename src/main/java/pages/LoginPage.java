package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final By demoLink = By.cssSelector("a[href='https://sbi.sberbank.ru:9444/ic/dcb/login.html?demoMode&version=3.0']");
    private final By loginField = By.id("inputLogin");
    private final By passwordField = By.id("inputPassword");
    private final By submitButton = By.xpath("//button[@data-test-id='LoginForm__submit--button']");

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

    /**
     * Войти в личный кабинет
     **/
    public MainPage signIn(String login, String password) {
        typeLogin(login);
        typePassword(password);
        clickSubmitButton();
        return new MainPage(driver);
    }

    /**
     * Ввести логин
     **/
    private void typeLogin(String login) {
        driver.findElement(loginField).sendKeys(login);
    }

    /**
     * Ввести пароль
     **/
    private void typePassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    /**
     * Нажать на кнопку "Войти"
     **/
    private void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }
}
