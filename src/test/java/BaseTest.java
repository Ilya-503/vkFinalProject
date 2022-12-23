import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    private static final String URL = "https://ok.ru";

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
    }

    @AfterEach
    public void close() {
        Selenide.closeWebDriver();
    }

   void openBrowser() {
        open(URL);
    }
}
