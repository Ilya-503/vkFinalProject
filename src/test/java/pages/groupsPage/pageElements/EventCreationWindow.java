package pages.groupsPage.pageElements;

import org.openqa.selenium.By;
import pages.groupsPage.factories.GroupCreationWindow;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.withTextCaseInsensitive;
import static com.codeborne.selenide.Selenide.$;

public class EventCreationWindow extends GroupCreationWindow {

    private static final By START_DATE_FIELD = byId("field_startDate");
    private static final By ADDRESS_FIELD = byId("field_address");
    private static final By CITY_FIELD = byId("field_city");
    private static final By LEGAL_CITY = withTextCaseInsensitive("Мали");
    private static final By PHONE_FIELD = byId("field_phone");
    private static final By WEBSITE_FIELD = byId("field_website");

    private static final By OLD_START_DATE_ERR = withTextCaseInsensitive("оканчиваться в прошлом");
    private static final By EMPTY_START_DATE_ERR = withTextCaseInsensitive("неправильное время");
    private static final By ILLEGAL_CITY_ERR = withTextCaseInsensitive("укажите город");
    private static final By EMPTY_ADDRESS_ERR = withTextCaseInsensitive("неправильный номер");
    private static final By ILLEGAL_PHONE_ERR = withTextCaseInsensitive("оканчиваться в прошлом");
    private static final By ILLEGAL_WEBSITE_ERR = withTextCaseInsensitive("неправильныц адрес сайта");

    public EventCreationWindow() {
        super();
    }

    public EventCreationWindow setStartDate(String startDate) {
        $(START_DATE_FIELD)
                .shouldBe(visible.because("Нет поля для ввода даты начала меро"))
                .clear();
        $(START_DATE_FIELD)
                .setValue(startDate);
        return this;
    }

    public EventCreationWindow setAddress(String address) {
        $(ADDRESS_FIELD)
                .shouldBe(visible.because("Нет поля для ввода адреса меро меро"))
                .clear();
        $(ADDRESS_FIELD)
                .setValue(address);
        return this;
    }

    public EventCreationWindow setLegalCity() {
        $(CITY_FIELD)
                .shouldBe(visible.because("Нет поля для ввода города"))
                .click();
        $(LEGAL_CITY)
                .shouldBe(visible.because("Нет легального города для клика"))
                .click();
        return this;
    }

    public EventCreationWindow setPhone(String phone) {
        $(PHONE_FIELD)
                .shouldBe(visible.because("Нет поля для ввода номера телефона у меро"))
                .clear();
        $(PHONE_FIELD)
                .setValue(phone);
        return this;
    }

    public EventCreationWindow setWebSite(String webSite) {
        $(WEBSITE_FIELD)
                .shouldBe(visible.because("Нет поля для ввода вебсайта у меро"))
                .setValue(webSite);
        return this;
    }

    public boolean isOldStartDate() {
        return $(OLD_START_DATE_ERR).is(visible);
    }

    public boolean isEmptyStartDate() {
        return $(EMPTY_START_DATE_ERR).is(visible);
    }

    public boolean isIllegalCity() {
        return $(ILLEGAL_CITY_ERR).is(visible);
    }

    public boolean isEmptyAddress() {
        return $(EMPTY_ADDRESS_ERR).is(visible);
    }

    public boolean isIllegalPhone() {
        return $(ILLEGAL_PHONE_ERR).is(visible);
    }

    public boolean isIllegalWebsite() {
        return $(ILLEGAL_WEBSITE_ERR).is(visible);
    }


}
