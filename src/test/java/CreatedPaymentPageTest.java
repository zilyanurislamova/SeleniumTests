import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreatedPaymentPageTest {
    WebDriver driver;
    LoginPage loginPage;
    MainPage mainPage;
    AccountsPaymentsPage accountsPaymentsPage;
    PaymentBetweenAccountsPage paymentBetweenAccountsPage;
    CreatedPaymentPage createdPaymentPage;
    By paymentStatus = By.cssSelector(".text-status");
    By successfulSigningNotification = By.xpath("//h2[text()='Платёжное поручение подписано']");
    By successfulSendingNotification = By.xpath("//h2[text()='Платёжное поручение отправлено']");
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
        paymentBetweenAccountsPage = (PaymentBetweenAccountsPage) accountsPaymentsPage.openPaymentCreationPage(PaymentType.BETWEENACCOUNTS);
        createdPaymentPage = paymentBetweenAccountsPage.createPaymentBetweenAccounts("22988,50");
    }

    @Test
    @DisplayName("Подписание платёжного поручения")
    void testSignPayment() {
        createdPaymentPage.signPayment("11111");
        output = driver.findElement(successfulSigningNotification).getText();
        assertEquals("Подписан", driver.findElement(paymentStatus).getText());
        System.out.println(output);
    }

    @Test
    @DisplayName("Отправка платёжного поручения на исполнение")
    void testPerformPayment() {
        createdPaymentPage.signPayment("11111").clickSendButton();
        output = driver.findElement(successfulSendingNotification).getText();
        assertEquals("Доставлен", driver.findElement(paymentStatus).getText());
        System.out.println(output);
    }

    @AfterEach
    void tearDown() {
        driver.close();
    }
}
