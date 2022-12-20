package pages.groupsPage.factories.creationWindow;

import org.openqa.selenium.By;
import pages.Loadable;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class GroupCreationWindow implements Loadable {

    protected static final By WINDOW_CONTAINER = byClassName("modal-new_center");
    protected static final By WINDOW_HEADER = byClassName("create-group-form_top");
    protected static final By TITLE_FIELD = byId("field_name");
    protected static final By THEME_DROPDOWN = byXpath("//*[contains(@class, 'multi-select-suggest-trigger')]");
    protected static final By LEGAL_THEME = byAttribute("data-value", "Кино");
    protected static final By CREATE_BTN = byAttribute("data-l", "t,confirm");
    protected static final By EMPTY_TITLE_ERR = withTextCaseInsensitive("Укажите название");
    protected static final By EMPTY_THEME_ERR = withTextCaseInsensitive("Укажите подкатегорию");
    protected static final By CLOSE_ICON = byId("nohook_modal_close");

    public GroupCreationWindow() {
        validate();
    }


    public <T extends GroupCreationWindow> T setTitle(String title) {
        $(TITLE_FIELD)
                .shouldBe(visible.because("Нет поля для ввода названия группы"))
                .clear();
        $(TITLE_FIELD)
                .setValue(title);
        return (T) this;
    }

    public <T extends GroupCreationWindow> T setLegalTheme() {
        $(THEME_DROPDOWN)
                .shouldBe(visible.because("Нет поля для выобра тематики группы"))
                .click();
        $(LEGAL_THEME)
                .shouldBe(visible.because("Не отображается тематика 'Кино'"))
                .click();
        return (T) this;
    }

    public void submitCreation() {
        $(CREATE_BTN)
                .shouldBe(visible.because("Нет кнопки подтверждения создания группы"))
                .click();

    }

    public void closeCreationWindow() {
        $(CLOSE_ICON)
                .shouldBe(visible.because("Нет крестика для закрытия окна создания группы"))
                .click();
    }

    public boolean isEmptyTitle() {
        return $(EMPTY_TITLE_ERR).is(visible);
    }

    public boolean isEmptyTheme() {
        return $(EMPTY_THEME_ERR).is(visible);
    }

    public static boolean isEventCrWn() {
        return $(WINDOW_HEADER)
                .shouldBe(visible.because("Не отображается хедер окна создания группы"))
                .innerText()
                .contains("Мероприятие");
    }

    @Override
    public void validate() {
        $(WINDOW_CONTAINER).shouldBe(visible.because("Не отображается окно создания группы"));
    }
}