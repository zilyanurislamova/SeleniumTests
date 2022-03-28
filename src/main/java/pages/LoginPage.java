package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.Keys.ENTER;

public class LoginPage {
    private final WebDriver driver;
    private final By demoLink = By.linkText("Войти в демо-режим");
    private final By loginField = By.id("inputLogin");
    private final By passwordField = By.id("inputPassword");

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
        driver.findElement(passwordField).sendKeys(password, ENTER);
    }
}
