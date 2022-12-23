package pages.groupPages;

import org.openqa.selenium.By;
import pages.groupsPage.factories.GroupPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Страница добавленной чужой группы
 */
public class OtherGroupPage extends GroupPage {

    public OtherGroupPage() {
        validate();
    }

    public void leaveGroup() {
        $(JOIN_OR_LEAVE_BTN)
                .shouldBe(visible.because("Нет кнопки выхода из группы"))
                .click();
        By exitGroupDropdown = byXpath("//*[@class='dropdown_n']");
        $(exitGroupDropdown).shouldBe(visible.because("Нет выпадающей кнопки выхода из группы")).click();
        By submitExit = byAttribute("data-l", "t,confirm");
        $(submitExit).should(visible.because("Нет окна подтверждения выходы из группы")).click();
    }
}
