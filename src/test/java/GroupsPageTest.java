import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pages.LeftNavigatePanel;
import pages.groupPages.MyGroupPage;
import pages.groupsPage.GroupCard;
import pages.groupsPage.GroupsPage;
import pages.groupsPage.factories.GroupPage;
import pages.groupsPage.pageElements.EventCreationWindow;
import pages.loginPage.LoginPage;
import pages.mainPage.MainPage;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.*;

public class GroupsPageTest extends BaseTest {

    private static final String LOGIN = "technoPol17";
    private static final String PASSWORD = "technoPolis2022";

    private GroupsPage groupsPage;

    @BeforeEach
    public void logging() {
        openBrowser();
        logIn();
        groupsPage = new MainPage().goToGroupsPage();
    }

    private void logIn() {
        new LoginPage()
                .setLogin(LOGIN)
                .setPassword(PASSWORD)
                .submit();
    }

    @Test
    @Tag("groupsPage")
    @DisplayName("Проверка добавления группы")
    public void addingGroupTest() {
        GroupCard firstCard = groupsPage.getFirstGroupCard();
        String title = firstCard.getTitle(), participAmount = firstCard.getParticipAmount();
        firstCard.joinGroup();
        GroupPage lastJoinedGroupPage = groupsPage.goToLastJoinedGroup();

        assertEquals(title, lastJoinedGroupPage.getGroupTitle(),
                "Название группы не совпало с ожидаемым");
        assertEquals(participAmount, lastJoinedGroupPage.getGroupMembersAmount(),
                "Кол-во участников группы не совпало с ожидаемым");
    }

    @Tags({@Tag("groupsPage"), @Tag("illegalData")})
    @DisplayName("Проверка создания мероприятия с неверными данными")
    @ParameterizedTest(name = "ID строки с данными: {0}")
    @CsvFileSource(files = "illegalDataCrEvTest.csv", numLinesToSkip = 1)
    public void createEventWithIllegalDataTest(String id, String startDate, String phone, String website,
                                               String title, String theme, String city, String address,
                                               String adr_err, String city_err, String thm_err, String title_err,
                                               String ph_err, String web_err, String old_start_date_err,
                                               String empty_start_date_err) {

        EventCreationWindow grCrWindow = groupsPage.goToEventGroupCreationWindow();
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

        assertAll(
                () -> assertVisibility(adr_err, grCrWindow.getEmptyAddressFieldErr(),
                        "введите адрес"),
                () -> assertVisibility(city_err, grCrWindow.getIllegalCityErr(),
                        "выберите город"),
                () -> assertVisibility(thm_err, grCrWindow.getIllegalThemeErr(),
                        "выберите тематику"),
                () -> assertVisibility(title_err, grCrWindow.getEmptyTitleFieldErr(),
                        "введите название"),
                () -> assertVisibility(ph_err, grCrWindow.getIllegalPhoneErr(),
                        "неверный номер телефона"),
                () -> assertVisibility(web_err, grCrWindow.getIllegalWebsiteErr(),
                        "неверный веб-сайт"),
                () -> assertVisibility(old_start_date_err, grCrWindow.getOldStartDateErr(),
                        "дата старта не может оканчиваться в прошлом"),
                () -> assertVisibility(empty_start_date_err, grCrWindow.getEmptyStartDateFieldErr(),
                        "неправильное время начала")
        );
        grCrWindow.closeCreationWindow();
    }
    private void assertVisibility(String testData, SelenideElement errElem, String errName) {
        if (testData.equals(TestDataType.TRUE.toString())) {
            errElem.shouldBe(visible.because(
                    String.format("Ошибку \n--%s--\nдолжно быть видно, но ее нет", errName))
            );
        } else {
           assertFalse(errElem.is(visible),
                   String.format("Ошибка \n--%s--\nне должна быть видна", errName)
           );
        }

        }

        @Tags({@Tag("groupsPage"), @Tag("legalData")})
        @DisplayName("Проверка создания мероприятия с верными данными")
        @ParameterizedTest(name = "ID передаваемых аргументов: {0}")
        @MethodSource("provideLegalGrCrParams")
        public void createEventWithLegalDataTest(String title, String phone, String website) {

        String startDate = "10.02.2030", address = "address";

        EventCreationWindow grCrWindow = groupsPage.goToEventGroupCreationWindow();
        grCrWindow
                .setStartDate(startDate)
                .setPhone(phone)
                .setWebSite(website)
                .setAddress(address)
                .setTitle(title)
                .setLegalCity()
                .setLegalTheme()
                .submitCreation();

        GroupPage createdGroupPage = new MyGroupPage();
        assertEquals(title, createdGroupPage.getGroupTitle());
    }

        private static Stream<Arguments> provideLegalGrCrParams() {
        return Stream.of(
                Arguments.of("title1", null, null),
                Arguments.of("title2", "89509508888", null),
                Arguments.of("title3", null, "https://ok.ru/")
        );
        }

        @AfterEach
        public void clean() {
        LeftNavigatePanel.goToGroupsPage();
        groupsPage.removeAllJoinedGroups();
    }
}
