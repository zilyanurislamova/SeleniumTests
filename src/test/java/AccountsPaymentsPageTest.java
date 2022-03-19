import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Создание платёжного поручения")
class AccountsPaymentsPageTest {
    WebDriver driver;
    LoginPage loginPage;
    MainPage mainPage;
    AccountsPaymentsPage accountsPaymentsPage;
    By paymentStatus = By.cssSelector(".text-status");

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
    @DisplayName("Создание платежа контрагенту")
    void testCreateCommercialPayment() {
        CommercialPaymentPage commercialPaymentPage = accountsPaymentsPage.openPaymentCreationPage(PaymentType.COMMERCIALPAYMENT);
        commercialPaymentPage.createCommercialPayment("77,78", "счет", "контр");
        assertEquals("Создан", driver.findElement(paymentStatus).getText());
        System.out.println("Платёж контрагенту успешно создан");
    }

    @Test
    @DisplayName("Создание платежа в бюджет")
    void testCreateBudgetPayment() {
        BudgetPaymentPage budgetPaymentPage = (BudgetPaymentPage) accountsPaymentsPage.openPaymentCreationPage(PaymentType.BUDGETPAYMENT);
        budgetPaymentPage.clickCreatePayeeButton().createPayee("БЮДЖЕТНАЯ ОРГАНИЗАЦИЯ", "7806000792", "780601001", "40101810523456789101", "041946000");
        budgetPaymentPage.createBudgetPayment("2022,02", "18210101011011000110", "19730000", "0", "108", "0");
        assertEquals("Создан", driver.findElement(paymentStatus).getText());
        System.out.println("Платёж в бюджет успешно создан");
    }

    @Test
    @DisplayName("Создание платежа между своими счетами")
    void testCreatePaymentBetweenAccounts() {
        PaymentBetweenAccountsPage paymentBetweenAccountsPage = (PaymentBetweenAccountsPage) accountsPaymentsPage.openPaymentCreationPage(PaymentType.BETWEENACCOUNTS);
        paymentBetweenAccountsPage.createPaymentBetweenAccounts("22988,50");
        assertEquals("Создан", driver.findElement(paymentStatus).getText());
        System.out.println("Платёж между своими счетами успешно создан");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

