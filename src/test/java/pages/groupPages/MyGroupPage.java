package pages.groupPages;

import org.openqa.selenium.By;
import pages.groupsPage.factories.groupPage.GroupPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class MyGroupPage extends GroupPage {

    private static final By OPTIONS_DROPDOWN = byXpath("//*[@class = 'u-menu_a toggle-dropdown']");

    public MyGroupPage() {
        validate();
    }

    @Override
    public void leaveGroup() {
        $(OPTIONS_DROPDOWN)
                .shouldBe(visible.because("Нет кнопки с функциями для группы"))
                .click();

        By removeGroup = byXpath("//*[contains(@class, 'svg-ico_trash')]/..");
        $(removeGroup)
                .shouldBe(visible.because("Нет кнопки удаления группы"))
                .click();

        By submitBtn = byAttribute("data-l", "t,confirm");
        $(submitBtn)
                .shouldBe(visible.because("Нет кнопки подтверждения удаления группы"))
                .click();
    }
}
