package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Основные операции над платёжным поручением")
class CreatedPaymentPageTest {
    WebDriver driver;
    LoginPage loginPage;
    MainPage mainPage;
    AccountsPaymentsPage accountsPaymentsPage;
    PaymentBetweenAccounts paymentBetweenAccounts;
    CreatedPaymentPage createdPaymentPage;
    By paymentStatus = By.className("text-status");
    By statusTracker = By.tagName("h2");
    By notification = By.className("notification-text");
    By number = By.cssSelector("td.col-number");
    By title = By.cssSelector("td[title]");
    By amount = By.cssSelector("span.value");

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
    @DisplayName("Подписание")
    void testSignPayment() {
        createdPaymentPage.signPayment("11111");
        assertEquals("Подписан", driver.findElement(paymentStatus).getText());
        System.out.println(driver.findElement(statusTracker).getText());
    }

    @Test
    @Smoke
    @DisplayName("Снятие подписи")
    void testRemoveSign() {
        createdPaymentPage.signPayment("11111").removeSign();
        assertEquals("Создан", driver.findElement(paymentStatus).getText());
        System.out.println("Подпись удалена - " + driver.findElement(statusTracker).getText().toLowerCase());
    }

    @Test
    @Regression
    @DisplayName("Подписание некорректным кодом")
    void testSignPaymentWithInvalidCode() {
        assertThrows(TimeoutException.class, () -> createdPaymentPage.signPayment("12345"));
        assertEquals("Создан", driver.findElement(paymentStatus).getText());
        System.out.println(driver.findElement(notification).getText());
    }

    @Test
    @Smoke
    @DisplayName("Отправка на исполнение")
    void testPerformPayment() {
        createdPaymentPage.signPayment("11111").sendPayment();
        assertEquals("Доставлен", driver.findElement(paymentStatus).getText());
        System.out.println(driver.findElement(statusTracker).getText());
    }

    @Test
    @Smoke
    @DisplayName("Копирование")
    void testCopyPayment() {
        createdPaymentPage.copyPayment();
        List<WebElement> numbers = driver.findElements(number);
        List<WebElement> titles = driver.findElements(title);
        List<WebElement> amounts = driver.findElements(amount);
        assertAll("Проверка номера, заголовка и суммы",
                () -> assertNotEquals(numbers.get(0).getText(), numbers.get(1).getText()),
                () -> assertEquals(titles.get(0).getText(), titles.get(1).getText()),
                () -> assertEquals(amounts.get(1).getText(), amounts.get(2).getText()));
        System.out.println("Номер оригинала: " + numbers.get(1).getText());
        System.out.println("Номер копии: " + numbers.get(0).getText());
    }

    @Test
    @Smoke
    @DisplayName("Редактирование")
    void testEditPayment() {
        createdPaymentPage.clickEditButton().editPayment("45977");
        assertEquals("45 977,00", driver.findElements(amount).get(1).getText());
        System.out.println("Платёжное поручение отредактировано");
    }

    @Test
    @Smoke
    @DisplayName("Удаление")
    void testDeletePayment() {
        createdPaymentPage.deletePayment();
        assertNotEquals("1", driver.findElement(number).getText());
        System.out.println("Платёжное поручение удалено");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
