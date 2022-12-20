package pages.groupsPage;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Карточка группы на странице с группами
 */
public class GroupCard {

    private static final By PARTICIP_AMOUNT = byClassName("friends-in-group_label");
    private static final By TITLE = byClassName("group-detailed-card_name");
    private static final By JOIN_BTN = byAttribute("data-l", "t,join");
    private static final By IN_GROUP_MSG = withTextCaseInsensitive("вы в группе");

    private final SelenideElement rootElem;

    protected GroupCard(SelenideElement rootElem) {
        this.rootElem = rootElem;
    }

    public String getTitle() {
        return rootElem
                .$(TITLE)
                .shouldBe(visible.because("Нет названия карточки группы"))
                .getText();
    }

    public String getParticipAmount() {
        return rootElem
                .$(PARTICIP_AMOUNT)
                .shouldBe(visible.because("Нет элемента для отрисовки кол-ва участников группы"))
                .getText()
                .split(" ")[0];
    }

    public void joinGroup() {
        rootElem
                .$(JOIN_BTN)
                .shouldBe(visible.because("Нет кнопки вступления в группу"))
                .click();
        $(IN_GROUP_MSG).shouldBe(visible.because("Нет сообщения о вступлении в группу"));
        Selenide.refresh(); // для отображения добавленной группы в верхней панели
    }
}
