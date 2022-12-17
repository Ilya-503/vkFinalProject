package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.groupsPage.GroupsPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.$;

public class LeftNavigatePanel {

    private final SelenideElement ROOT_ELEM = $(byAttribute("data-l", "t,navigation"));
    private static final By USER_PAGE = byAttribute("data-l", "t,userPage");
    private static final By NEWS_PAGE = byAttribute("data-l", "t,userMain");
    private static final By FRIENDS = byAttribute("data-l", "t,userFriend");
    private static final By PHOTOS = byAttribute("data-l", "t,userPhotos");
    private static final By GROUPS = byAttribute("data-l", "t,userAltGroup");


    public LeftNavigatePanel() {

    }

    protected GroupsPage goToGroupsPage() {
        ROOT_ELEM.$(GROUPS).shouldBe(visible.because("Нет кнопки перехода на страницу групп")).click();
        return new GroupsPage();
    }

    public void isVisible() {
        $(ROOT_ELEM).shouldBe(visible.because("Нет левой панели навигации"));
    }

}
