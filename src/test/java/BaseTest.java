import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import pages.loginPage.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    private static final String LOGIN = "technoPol17";
    private static final String PASSWORD = "technoPolis2022";

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.baseUrl = "https://ok.ru/";
        Configuration.holdBrowserOpen = true;
    }

    public void logIn() {
        open(Configuration.baseUrl);
        new LoginPage()
                .setLogin(LOGIN)
                .setPassword(PASSWORD)
                .submit();
    }
}
