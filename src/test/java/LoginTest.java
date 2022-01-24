import org.junit.*;

public class LoginTest {
    @Test
    public void shouldLogin() {
        Login loginPage = new Login();
        loginPage.openPage("http://10.36.252.39:9080/ic/dcb/login.html?ReturnTo=%2Faccounts-payments#/");
        loginPage.typeLogin("riart_ch");
        loginPage.typePassword("zaq!xsw");
        loginPage.clickEnterButton();
        loginPage.typeCode("1");
    }
}
