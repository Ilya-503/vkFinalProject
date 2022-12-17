package pages.groupsPage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.LeftNavigatePanel;
import pages.loginPage.Loadable;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class GroupsPage implements Loadable {

    private static final By GROUPS_TOOLS_CONTAINER = byXpath("//*[contains(@id, 'UserGroupsCatalogHeaderBlock')]");
    private static final By JOINED_GROUPS_PANEL = byAttribute("data-l", "t,scroll-slider");
    private static final By JOINED_GROUP_LOCATOR = byAttribute("data-l", "t,visit");
    private static final By GROUP_CARD_LOCATOR =
            byXpath("//*[contains(@data-l, 'groupCard,POPULAR_GROUPS.popularTop')]");

    public GroupsPage() {
        validate();
    }

    public GroupCard getFirstGroupCard() {
        $(GROUP_CARD_LOCATOR).shouldBe(visible.because("Нет карточек групп"));
        return new GroupCard($(GROUP_CARD_LOCATOR));
    }

    public ElementsCollection getAllJoinedGroups() {
        Selenide.refresh(); // если только что вступили -> для отображения
        return $(JOINED_GROUPS_PANEL)
                .shouldBe(visible.because("Нет панели с добавленными группами"))  // if no groups (event not fail) -> EXCEPTION
                .$$(JOINED_GROUP_LOCATOR);
    }

    public void goToLastJoinedGroup() {
        Selenide.refresh(); // если только что вступили -> для отображения
        $(JOINED_GROUPS_PANEL)
                .shouldBe(visible.because("Нет панели с добавленными группами"))
                .$(JOINED_GROUP_LOCATOR)
                .shouldBe(visible.because("Нет иконки добавленной группы"))
                .click();
    }

    @Override
    public void validate() {
        $(GROUPS_TOOLS_CONTAINER).shouldBe(visible.because("Нет верхней панели на странице групп"));
        LeftNavigatePanel.isVisible();
    }
}
