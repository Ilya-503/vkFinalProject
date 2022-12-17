package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import pages.loginPage.Loadable;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class CurrentGroupPage implements Loadable {

    private static final By HEAD_PANEL = byAttribute("data-l", "t,main-content-header");
    private static final By RIGHT_INFO_PANEL = byClassName("layout-sticky-column");

    private static final By GROUP_TITLE = byClassName("group-name_h");
    private static final By PARTICIP_AMOUNT = byXpath("//*[contains(@data-l, 'GroupMembers')]");

    public CurrentGroupPage() {
        validate();
    }

    public String getGroupTitle() {
        return $(GROUP_TITLE)
                .shouldBe(visible.because("Нет элемента с названием группы"))
                .getText();
    }

    public String getGroupMembersAmount() {
        return $(PARTICIP_AMOUNT)
                .shouldBe(visible.because("Нет элемента с кол-вом участников группы"))
                .getText()
                .split(" ")[1];
    }

    @Override
    public void validate() {
        $(HEAD_PANEL).shouldBe(visible.because("Нет верхней панели с инфо о группе"));
        $(RIGHT_INFO_PANEL).shouldBe(visible.because("Нет правой панели с инфо о группе"));
        LeftNavigatePanel.isVisible();
    }
}
