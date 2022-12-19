import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.currentGroupPage.CurrentGroupPage;
import pages.LeftNavigatePanel;
import pages.groupsPage.GroupCard;
import pages.groupsPage.GroupsPage;
import pages.groupsPage.factories.GroupCreationWindow;
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
    public void testAddingGroup() {
        GroupCard firstCard = groupsPage.getFirstGroupCard();
        firstCard.joinGroup();
        String title = firstCard.getTitle(), participAmount = firstCard.getParticipAmount();
        Selenide.refresh();   // для отображения в панели добавленных груп
        CurrentGroupPage lastGroupPage = groupsPage.goToLastJoinedGroup();

        assertEquals(title, lastGroupPage.getGroupTitle(), "Название группы не совпало с ожидаемым");
        assertEquals(participAmount, lastGroupPage.getGroupMembersAmount(),
                "Кол-во участников группы не совпало с ожидаемым");
    }

    @ParameterizedTest
    @CsvFileSource(files = "illegalDataCrEvTest.csv", numLinesToSkip = 1) // DATE FIX
    public void createEventWithIllegalDataTest(String id, String startDate, String phone, String website, String title,
                         String theme, String city, String address,
                         String adr_err, String city_err, String thm_err, String title_err,
                         String ph_err, String web_err, String date_err) throws Exception {

        EventCreationWindow grCrWindow = (EventCreationWindow) groupsPage.createGroup(2); // FIX THIS SHIT
        grCrWindow.setStartDate(startDate);
        grCrWindow.setPhone(phone);
        grCrWindow.setWebSite(website);
        grCrWindow.setTitle(title);
        grCrWindow.setAddress(address);

        if (theme != null) {
            grCrWindow.setLegalTheme();
        }
        if (city != null) {
            grCrWindow.setLegalCity();
        }

        grCrWindow.submitCreation();  // find where ILLEGAL to EMPTY
        Thread.sleep(700);

        assertAll(
                () -> assertEquals(adr_err.equals("TRUE"), grCrWindow.isEmptyAddress(), "ошибка адреса"),
                () -> assertEquals(city_err.equals("TRUE"), grCrWindow.isIllegalCity(), "ошибка города"),
                () -> assertEquals(thm_err.equals("TRUE"), grCrWindow.isIllegalTheme(), "ошибка тематики"),
                () -> assertEquals(title_err.equals("TRUE"), grCrWindow.isEmptyTitle(), "ошибка названия"),
                () -> assertEquals(ph_err.equals("TRUE"), grCrWindow.isIllegalPhone(), "ошибка номера"),
                () -> assertEquals(web_err.equals("TRUE"), grCrWindow.isIllegalWebsite(), "ошибка сайта"),
                () -> {
                    switch (date_err) {
                        case "1" -> assertTrue(grCrWindow.isEmptyStartDate(), "ошибка пустой даты");
                        case "2" -> assertTrue(grCrWindow.isOldStartDate(), "ошибка старой даты");
                    }
                }
        );
        }



    @AfterEach
    public void clean() {
        Selenide.closeWebDriver();
//        LeftNavigatePanel.goToGroupsPage();
//        groupsPage.removeAllJoinedGroups();
    }
}
