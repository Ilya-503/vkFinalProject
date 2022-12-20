package pages.mainPage;

import org.openqa.selenium.By;
import pages.LeftNavigatePanel;
import pages.groupsPage.GroupsPage;
import pages.Loadable;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class MainPage implements Loadable {

    private static final By UPPER_TOOL_BAR = byAttribute("data-l", "t,navigationToolbar");

    public MainPage() {
        validate();
    }

    public GroupsPage goToGroupsPage() {
        LeftNavigatePanel.goToGroupsPage();
        return new GroupsPage();
    }

    @Override
    public void validate() {
        $(UPPER_TOOL_BAR).shouldBe(visible.because("Нет верхней панели навигации"));
        LeftNavigatePanel.isVisible();
    }
}
