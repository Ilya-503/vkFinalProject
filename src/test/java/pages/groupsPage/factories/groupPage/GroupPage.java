package pages.groupsPage.factories.groupPage;

import org.openqa.selenium.By;
import pages.LeftNavigatePanel;
import pages.Loadable;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public abstract class GroupPage implements Loadable {

    private static final By HEAD_PANEL = byAttribute("data-l", "t,main-content-header");
    private static final By RIGHT_INFO_PANEL = byClassName("layout-sticky-column");
    private static final By GROUP_TITLE = byClassName("group-name_h");
    private static final By PARTICIP_AMOUNT = byXpath("//*[contains(@data-l, 'GroupMembers')]");
    private static final By PROMOTE_ICON = byXpath("//*[contains(@class, 'svg-ico_promote_16')]");

    abstract public void leaveGroup();

    public static boolean isMyGroup() {
        return $(PROMOTE_ICON).is(visible);
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
