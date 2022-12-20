package pages.groupPages;

import org.openqa.selenium.By;
import pages.groupsPage.factories.groupPage.GroupPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница добавленной чужой группы
 */
public class OtherGroupPage extends GroupPage {

    private static final By EXIT_GROUP_BTN = byXpath("//*[contains(@class, 'dropdown_ac')]");

    public OtherGroupPage() {
        validate();
    }

    public void leaveGroup() {
        $(EXIT_GROUP_BTN)
                .shouldBe(visible.because("Нет кнопки выхода из группы"))
                .click();
        By exitGroupDropdown = byXpath("//*[@class='dropdown_n']");
        $(exitGroupDropdown).shouldBe(visible.because("Нет выпадающей кнопки выхода из группы")).click();
        By submitExit = byAttribute("data-l", "t,confirm");
        $(submitExit).should(visible.because("Нет окна подтверждения выходы из группы")).click();
    }
}
