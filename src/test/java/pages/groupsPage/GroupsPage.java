package pages.groupsPage;

import org.openqa.selenium.By;
import pages.currentGroupPage.CurrentGroupPage;
import pages.LeftNavigatePanel;
import pages.Loadable;
import pages.groupsPage.factories.GroupCreationWindow;
import pages.groupsPage.factories.GroupCreationWindowFactory;
import pages.groupsPage.pageElements.EventCreationWindow;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class GroupsPage implements Loadable {

    private static final By GROUPS_TOOLS_CONTAINER = byXpath("//*[contains(@id, 'UserGroupsCatalogHeaderBlock')]");
    private static final By JOINED_GROUPS_PANEL = byAttribute("data-l", "t,scroll-slider");
    private static final By JOINED_GROUP_LOCATOR = byAttribute("data-l", "t,visit");
    private static final By CREATE_GROUP_BTN = byXpath("//*[contains(@class, 'groups-catalog-header_button')]");
    private static final By GROUP_CARD_LOCATOR =
            byXpath("//*[contains(@data-l, 'groupCard,POPULAR_GROUPS.popularTop')]");

    public GroupsPage() {
        validate();
    }

    public GroupCard getFirstGroupCard() {
        $(GROUP_CARD_LOCATOR).shouldBe(visible.because("Нет карточек групп"));
        return new GroupCard($(GROUP_CARD_LOCATOR));
    }

    public CurrentGroupPage goToLastJoinedGroup() {
        $(JOINED_GROUPS_PANEL)
                .shouldBe(visible.because("Нет панели с добавленными группами"))
                .$(JOINED_GROUP_LOCATOR)
                .shouldBe(visible.because("Нет иконки добавленной группы"))
                .click();
        return new CurrentGroupPage();
    }

    public void removeAllJoinedGroups() {
        while ($(JOINED_GROUPS_PANEL).is(visible)) {
            CurrentGroupPage curGroupPage = goToLastJoinedGroup();
            curGroupPage.exitGroup();
            LeftNavigatePanel.goToGroupsPage();
        }

    }

    public GroupCreationWindow createGroup() { // FIX
        $(CREATE_GROUP_BTN)
                .shouldBe(visible.because("Нет кнопки для создания группы"))
                .click();
        return GroupCreationWindowFactory.getGroupCreationWindow();
    }

    @Override
    public void validate() {
        $(GROUPS_TOOLS_CONTAINER).shouldBe(visible.because("Нет верхней панели на странице групп"));
        LeftNavigatePanel.isVisible();
    }
}
