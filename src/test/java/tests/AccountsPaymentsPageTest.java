package tests;

import tests.annotations.Smoke;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import pages.enums.PaymentType;
import pages.payments.BudgetPayment;
import pages.payments.CommercialPayment;
import pages.payments.PaymentBetweenAccounts;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Создание платёжного поручения")
class AccountsPaymentsPageTest {
    WebDriver driver;
    LoginPage loginPage;
    MainPage mainPage;
    AccountsPaymentsPage accountsPaymentsPage;
    By paymentStatus = By.className("text-status");

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sbi.sberbank.ru:9443/ic/dcb/?#/");
        loginPage = new LoginPage(driver);
        mainPage = loginPage.login();
        accountsPaymentsPage = mainPage.openAccountsPaymentsPage();
    }

    @Test
    @Smoke
    @DisplayName("Создание платежа контрагенту")
    void testCreateCommercialPayment() {
        CommercialPayment commercialPayment = accountsPaymentsPage.openPaymentCreationPage(PaymentType.COMMERCIALPAYMENT);
        commercialPayment.createPayment("77,78", "счет", "контр");
        assertEquals("Создан", driver.findElement(paymentStatus).getText());
        System.out.println("Платёж контрагенту создан");
    }

    @Test
    @Smoke
    @DisplayName("Создание платежа в бюджет")
    void testCreateBudgetPayment() {
        BudgetPayment budgetPayment = (BudgetPayment) accountsPaymentsPage.openPaymentCreationPage(PaymentType.BUDGETPAYMENT);
        budgetPayment.clickCreatePayeeButton().createPayee("БЮДЖЕТНАЯ ОРГАНИЗАЦИЯ", "7806000792", "780601001", "40101810523456789101", "041946000");
        budgetPayment.createPayment("2022,02", "18210101011011000110", "19730000", "0", "108", "0");
        assertEquals("Создан", driver.findElement(paymentStatus).getText());
        System.out.println("Платёж в бюджет создан");
    }

    @Test
    @Smoke
    @DisplayName("Создание платежа между своими счетами")
    void testCreatePaymentBetweenAccounts() {
        PaymentBetweenAccounts paymentBetweenAccounts = (PaymentBetweenAccounts) accountsPaymentsPage.openPaymentCreationPage(PaymentType.BETWEENACCOUNTS);
        paymentBetweenAccounts.createPayment("22988,50");
        assertEquals("Создан", driver.findElement(paymentStatus).getText());
        System.out.println("Платёж между своими счетами создан");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

