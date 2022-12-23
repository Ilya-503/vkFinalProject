package pages.groupsPage.pageElements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.withTextCaseInsensitive;
import static com.codeborne.selenide.Selenide.$;

/**
 * Оконо создания группы по мероприятию (флаг <b>3</b>)
 */
public class EventCreationWindow extends GroupCreationWindow {

    private static final By START_DATE_FIELD = byId("field_startDate");
    private static final By ADDRESS_FIELD = byId("field_address");
    private static final By CITY_FIELD = byId("field_city");
    private static final By LEGAL_CITY = withTextCaseInsensitive("Мали");
    private static final By PHONE_FIELD = byId("field_phone");
    private static final By WEBSITE_FIELD = byId("field_website");

    private static final By OLD_START_DATE_ERR = withTextCaseInsensitive("оканчиваться в прошлом");
    private static final By EMPTY_START_DATE_ERR = withTextCaseInsensitive("неправильное время");
    private static final By EMPTY_CITY_ERR = withTextCaseInsensitive("Укажите город");
    private static final By EMPTY_ADDRESS_ERR = withTextCaseInsensitive("Укажите адрес");
    private static final By ILLEGAL_PHONE_ERR = withTextCaseInsensitive("Неправильный номер");
    private static final By ILLEGAL_WEBSITE_ERR = withTextCaseInsensitive("неправильный адрес сайта");
    private static final By EMPTY_THEME_ERR = withTextCaseInsensitive("Укажите категорию");

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
                .clear();
        $(WEBSITE_FIELD)
                .setValue(webSite);
        return this;
    }

    public EventCreationWindow setTitle(String title) {
        return (EventCreationWindow) super.setTitle(title);
    }

    public EventCreationWindow setLegalTheme() {
        return (EventCreationWindow) super.setLegalTheme();
    }

    @Override
    public SelenideElement getIllegalThemeErr() {            // т.к. новый локатор EMPTY_THEME_ERR
        return $(EMPTY_THEME_ERR);
    }

    public SelenideElement getOldStartDateErr() {
        return $(OLD_START_DATE_ERR);
    }

    public SelenideElement getEmptyStartDateFieldErr() {
        return $(EMPTY_START_DATE_ERR);
    }

    public SelenideElement getIllegalCityErr() {
        return $(EMPTY_CITY_ERR);
    }

    public SelenideElement getEmptyAddressFieldErr() {
        return $(EMPTY_ADDRESS_ERR);
    }

    public SelenideElement getIllegalPhoneErr() {
        return $(ILLEGAL_PHONE_ERR);
    }

    public SelenideElement getIllegalWebsiteErr() {
        return $(ILLEGAL_WEBSITE_ERR);
    }
}
