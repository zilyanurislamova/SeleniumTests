package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AccountsPaymentsPage;
import pages.CreatedPaymentPage;
import pages.LoginPage;
import pages.MainPage;
import pages.enums.PaymentType;
import pages.payments.PaymentBetweenAccounts;
import tests.annotations.Regression;
import tests.annotations.Smoke;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Подписание и отправка на исполнение")
class CreatedPaymentPageTest {
    WebDriver driver;
    LoginPage loginPage;
    MainPage mainPage;
    AccountsPaymentsPage accountsPaymentsPage;
    PaymentBetweenAccounts paymentBetweenAccounts;
    CreatedPaymentPage createdPaymentPage;
    By paymentStatus = By.cssSelector(".text-status");
    By creationNotification = By.xpath("//h2[text()='Платёжное поручение создано']");
    By signingNotification = By.xpath("//h2[text()='Платёжное поручение подписано']");
    By sendingNotification = By.xpath("//h2[text()='Платёжное поручение отправлено']");
    By notification = By.className("notification-text");
    String output;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sbi.sberbank.ru:9443/ic/dcb/?#/");
        loginPage = new LoginPage(driver);
        mainPage = loginPage.login();
        accountsPaymentsPage = mainPage.openAccountsPaymentsPage();
        paymentBetweenAccounts = (PaymentBetweenAccounts) accountsPaymentsPage.openPaymentCreationPage(PaymentType.BETWEENACCOUNTS);
        createdPaymentPage = paymentBetweenAccounts.createPayment("22988,50");
    }

    @Test
    @Smoke
    @DisplayName("Подписание платёжного поручения")
    void testSignPayment() {
        createdPaymentPage.signPayment("11111");
        output = driver.findElement(signingNotification).getText();
        assertEquals("Подписан", driver.findElement(paymentStatus).getText());
        System.out.println(output);
    }

    @Test
    @Smoke
    @DisplayName("Снятие подписи с платёжного поручения")
    void testRemoveSign() {
        createdPaymentPage.signPayment("11111").removeSign();
        output = driver.findElement(creationNotification).getText();
        assertEquals("Создан", driver.findElement(paymentStatus).getText());
        System.out.println(output);
    }

    @Test
    @Regression
    @DisplayName("Подписание платёжного поручения некорректным кодом")
    void testSignPaymentWithInvalidCode() {
        createdPaymentPage.signPayment("12345");
        output = driver.findElement(notification).getText();
        assertEquals("Создан", driver.findElement(paymentStatus).getText());
        System.out.println(output);
    }

    @Test
    @Smoke
    @DisplayName("Отправка платёжного поручения на исполнение")
    void testPerformPayment() {
        createdPaymentPage.signPayment("11111").sendPayment();
        output = driver.findElement(sendingNotification).getText();
        assertEquals("Доставлен", driver.findElement(paymentStatus).getText());
        System.out.println(output);
    }

    @Test
    @Smoke
    @DisplayName("Копирование платёжного поручения")
    void testCopyPayment() {
        createdPaymentPage.copyPayment();
    }

    @Test
    @Smoke
    @DisplayName("Редактирование платёжного поручения")
    void testEditPayment() {
        createdPaymentPage.clickEditButton().editPayment("45977");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
