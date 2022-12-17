package pages.groupsPage;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import pages.loginPage.Loadable;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class GroupsPage implements Loadable {

    private static final By UPPER_CONTAINER = byXpath("//*[contains(@id, 'UserGroupsPanelBlock')]");


    @Override
    public void validate() {
        $(UPPER_CONTAINER).shouldBe(visible.because("Нет верхней панели на странице групп"));
    }
}
