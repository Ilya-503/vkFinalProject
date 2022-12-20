import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.LeftNavigatePanel;
import pages.groupsPage.GroupCard;
import pages.groupsPage.GroupsPage;
import pages.groupsPage.factories.groupPage.GroupPage;
import pages.groupsPage.pageElements.EventCreationWindow;
import pages.mainPage.MainPage;

import static org.junit.jupiter.api.Assertions.*;

public class GroupsPageTest extends BaseTest {

    private GroupsPage groupsPage;

    @BeforeEach
    public void logging() {
        logIn();
        groupsPage = new MainPage().goToGroupsPage();
    }

    @Test
    @Tag("groupsPage")
    @DisplayName("Проверка добавления группы")
    public void testAddingGroup() {
        GroupCard firstCard = groupsPage.getFirstGroupCard();
        String title = firstCard.getTitle(), participAmount = firstCard.getParticipAmount();
        firstCard.joinGroup();
        GroupPage lastGroupPage = groupsPage.goToLastJoinedGroup();

        assertEquals(title, lastGroupPage.getGroupTitle(),
                "Название группы не совпало с ожидаемым");
        assertEquals(participAmount, lastGroupPage.getGroupMembersAmount(),
                "Кол-во участников группы не совпало с ожидаемым");
    }

    @Tags({@Tag("groupsPage"), @Tag("illegalData")})
    @DisplayName("Проверка создания мероприятия с неверными данными")
    @ParameterizedTest(name = "ID строки с данными: {0}")
    @CsvFileSource(files = "illegalDataCrEvTest.csv", numLinesToSkip = 1)
    public void createEventWithIllegalDataTest(String id, String startDate, String phone, String website, String title,
                         String theme, String city, String address,
                         String adr_err, String city_err, String thm_err, String title_err,
                         String ph_err, String web_err, String date_err) throws Exception {

        EventCreationWindow grCrWindow = (EventCreationWindow) groupsPage.createGroup(2);
        grCrWindow
                .setStartDate(startDate)
                .setPhone(phone)
                .setWebSite(website)
                .setAddress(address)
                .setTitle(title);

        if (theme != null) {
            grCrWindow.setLegalTheme();
        }
        if (city != null) {
            grCrWindow.setLegalCity();
        }

        grCrWindow.submitCreation();
        Thread.sleep(700);  // FIX THIS

        assertAll(
                () -> assertEquals(adr_err.equals("TRUE"), grCrWindow.isEmptyAddress(),
                        "Не совпадает видимость ошибки адреса"),
                () -> assertEquals(city_err.equals("TRUE"), grCrWindow.isEmptyCity(),
                        "Не совпадаает видимость ошибки города"),
                () -> assertEquals(thm_err.equals("TRUE"), grCrWindow.isEmptyTheme(),
                        "Не совпадает видимость ошибки тематики"),
                () -> assertEquals(title_err.equals("TRUE"), grCrWindow.isEmptyTitle(),
                        "Не совпадает видимость ошибки названия"),
                () -> assertEquals(ph_err.equals("TRUE"), grCrWindow.isIllegalPhone(),
                        "Не совпадает видимость ошибки номера"),
                () -> assertEquals(web_err.equals("TRUE"), grCrWindow.isIllegalWebsite(),
                        "Не совпадает видимость ошибки сайта"),
                () -> {
                    switch (date_err) {
                        case "0" -> {
                            assertFalse(grCrWindow.isEmptyStartDate(),
                                    "Ошибка пустой даты не должна быть видна");
                            assertFalse(grCrWindow.isOldStartDate(), "" +
                                    "Ошибка старой даты не должна быть видна");
                        }
                        case "1" -> assertTrue(grCrWindow.isEmptyStartDate(),
                                "Не совпадает видимость ошибки пустой даты");
                        case "2" -> assertTrue(grCrWindow.isOldStartDate(),
                                "Не совпадает видимость ошибки старой даты");
                    }
                }
        );
        grCrWindow.closeCreationWindow();
        }

    @AfterEach
    public void clean() {
        LeftNavigatePanel.goToGroupsPage();
        groupsPage.removeAllJoinedGroups();
    }
}
