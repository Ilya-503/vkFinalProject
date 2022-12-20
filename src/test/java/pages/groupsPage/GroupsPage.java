package pages.groupsPage;

import org.openqa.selenium.By;
import pages.groupPages.StandardGroupPage;
import pages.LeftNavigatePanel;
import pages.Loadable;
import pages.groupsPage.factories.creationWindow.GroupCreationWindow;
import pages.groupsPage.factories.creationWindow.GroupCreationWindowFactory;
import pages.groupsPage.factories.groupPage.GroupPage;
import pages.groupsPage.factories.groupPage.GroupPageFactory;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Страница с группами (группы юзера, популярные и т.д.)
 */
public class GroupsPage implements Loadable {

    private static final By GROUPS_TOOLS_CONTAINER = byXpath("//*[contains(@id, 'UserGroupsCatalogHeaderBlock')]");
    private static final By JOINED_GROUPS_PANEL = byAttribute("data-l", "t,scroll-slider");
    private static final By JOINED_GROUP_LOCATOR = byAttribute("data-l", "t,visit");
    private static final By CREATE_GROUP_BTN = withTextCaseInsensitive("Создать группу");
    private static final By CREATE_PUBLIC_GR = byAttribute("data-l", "t,PAGE");
    private static final By CREATE_INTEREST_GR = byAttribute("data-l", "t,INTEREST");
    private static final By CREATE_EVENT = byAttribute("data-l", "t,HAPPENING");
    private static final By GROUP_CARD_LOCATOR =
            byXpath("//*[contains(@data-l, 'groupCard,POPULAR_GROUPS.popularTop')]");

    public GroupsPage() {
        validate();
    }

    public GroupCard getFirstGroupCard() {
        $(GROUP_CARD_LOCATOR).shouldBe(visible.because("Нет карточек групп"));
        return new GroupCard($(GROUP_CARD_LOCATOR));
    }

    public GroupPage goToLastJoinedGroup() {
        $(JOINED_GROUPS_PANEL)
                .shouldBe(visible.because("Нет панели с добавленными группами"))
                .$(JOINED_GROUP_LOCATOR)
                .shouldBe(visible.because("Нет иконки добавленной группы"))
                .click();
        return GroupPageFactory.getGroupPage();
    }

    public void removeAllJoinedGroups() {
        while ($(JOINED_GROUPS_PANEL).is(visible)) {
            GroupPage curGroupPage = goToLastJoinedGroup();
            curGroupPage.leaveGroup();
            LeftNavigatePanel.goToGroupsPage();
        }

    }

    /**
     * @param flag какую группу создать?
     *             <p> 0 - публичную    </p>
     *             <p> 1 - по интересам </p>
     *             <p> 2 - мероприятие  </p>
     * @return панель создания группы
     */
    public GroupCreationWindow createGroup(int flag) {
        $(CREATE_GROUP_BTN)
                .shouldBe(visible.because("Нет кнопки для создания группы"))
                .click();
        switch (flag) {
            case 0 -> $(CREATE_PUBLIC_GR)
                    .shouldBe(visible.because("Нет кнопки для создания публичной группы"))
                    .click();
            case 1 -> $(CREATE_INTEREST_GR)
                    .shouldBe(visible.because("Нет кнопки для создания группы по интересам"))
                    .click();
            case 2 -> $(CREATE_EVENT)
                    .shouldBe(visible.because("Нет кнопки для создания мероприятия"))
                    .click();
            default -> fail("Использован неверный флаг при создании группы!");
        }
        return GroupCreationWindowFactory.getGroupCreationWindow();
    }

    @Override
    public void validate() {
        $(GROUPS_TOOLS_CONTAINER).shouldBe(visible.because("Нет верхней панели на странице групп"));
        LeftNavigatePanel.isVisible();
    }
}
