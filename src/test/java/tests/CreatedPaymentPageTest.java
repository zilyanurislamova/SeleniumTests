package tests;

import tests.annotations.Regression;
import tests.annotations.Smoke;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import pages.enums.PaymentType;
import pages.payments.PaymentBetweenAccounts;

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
    By successfulCreationNotification = By.xpath("//h2[text()='Платёжное поручение создано']");
    By successfulSigningNotification = By.xpath("//h2[text()='Платёжное поручение подписано']");
    By successfulSendingNotification = By.xpath("//h2[text()='Платёжное поручение отправлено']");
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
        createdPaymentPage = paymentBetweenAccounts.createPaymentBetweenAccounts("22988,50");
    }

    @Test
    @Smoke
    @DisplayName("Подписание платёжного поручения")
    void testSignPayment() {
        createdPaymentPage.signPayment("11111");
        output = driver.findElement(successfulSigningNotification).getText();
        assertEquals("Подписан", driver.findElement(paymentStatus).getText());
        System.out.println(output);
    }

    @Test
    @Smoke
    @DisplayName("Снятие подписи с платёжного поручения")
    void testRemoveSign() {
        createdPaymentPage.signPayment("11111").removeSign();
        output = driver.findElement(successfulCreationNotification).getText();
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
        createdPaymentPage.signPayment("11111").clickSendButton();
        output = driver.findElement(successfulSendingNotification).getText();
        assertEquals("Доставлен", driver.findElement(paymentStatus).getText());
        System.out.println(output);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
