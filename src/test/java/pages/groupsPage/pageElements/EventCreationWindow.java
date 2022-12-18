package pages.groupsPage.pageElements;

import org.openqa.selenium.By;
import pages.groupsPage.factories.GroupCreationWindow;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.withTextCaseInsensitive;

public class EventCreationWindow extends GroupCreationWindow {

    private static final By START_DATE_FIELD = byId("field_startDate");
    private static final By ADDRESS_FIELD = byId("field_address");
    private static final By PHONE_FIELD = byId("field_phone");
    private static final By WEBSITE_FIELD = byId("field_website");

    private static final By OLD_START_DATE_ERR = withTextCaseInsensitive("оканчиваться в прошлом");
    private static final By ILLEGAL_CITY_ERR = withTextCaseInsensitive("укажите город");
    private static final By ILLEGAL_ADDRESS_ERR = withTextCaseInsensitive("неправильный номер");
    private static final By ILLEGAL_PHONE_ERR = withTextCaseInsensitive("оканчиваться в прошлом");
    private static final By ILLEGAL_WEBSITE_ERR = withTextCaseInsensitive("неправильныц адрес сайта");

    public EventCreationWindow() {
        super();
    }
}
