package pages.mainPage;

import org.openqa.selenium.By;
import pages.LeftNavigatePanel;
import pages.loginPage.Loadable;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class MainPage implements Loadable {

    private final LeftNavigatePanel leftNavigatePanel;

    private static final By UPPER_TOOL_BAR = byAttribute("data-l", "t,navigationToolbar");

    public MainPage() {
        validate();
        leftNavigatePanel = new LeftNavigatePanel();
    }

    @Override
    public void validate() {
        $(UPPER_TOOL_BAR).shouldBe(visible.because("Нет верхней панели навигации"));
        leftNavigatePanel.isVisible();
    }
}
