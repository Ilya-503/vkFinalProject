package pages.mainPage;

import org.openqa.selenium.By;
import pages.loginPage.Loadable;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class MainPage implements Loadable {

    private final LeftNavigatePanel leftNavigatePanel;

    private static final By UPPER_TOOL_BAR = byAttribute("data-l", "t,navigationToolbar");
    private static final By LEFT_NAVIGATION_PANEL = byAttribute("data-l" ,"t,navigation");

    public MainPage() {
        validate();
        leftNavigatePanel = new LeftNavigatePanel($(LEFT_NAVIGATION_PANEL));
    }

    @Override
    public void validate() {
        $(UPPER_TOOL_BAR).shouldBe(visible.because("Нет верхней панели навигации"));
        $(LEFT_NAVIGATION_PANEL).shouldBe(visible.because("Нет левой панели навигации"));
    }
}
