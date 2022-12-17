package pages.mainPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;

public class LeftNavigatePanel {

    private final SelenideElement ROOT_ELEM;

    private static final By USER_PAGE = byAttribute("data-l", "t,userPage");
    private static final By NEWS_PAGE = byAttribute("data-l", "t,userMain");
    private static final By FRIENDS = byAttribute("data-l", "t,userFriend");
    private static final By PHOTOS = byAttribute("data-l", "t,userPhotos");
    private static final By GROUPS = byAttribute("data-l", "t,userAltGroup");


    protected LeftNavigatePanel(SelenideElement rootElem) {
        ROOT_ELEM = rootElem;
    }

    protected void goToGroupsPage() {
        ROOT_ELEM.$(GROUPS).shouldBe(visible.because("Нет кнопки перехода на страницу групп"));
    }

}
