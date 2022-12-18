import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CurrentGroupPage;
import pages.LeftNavigatePanel;
import pages.groupsPage.GroupCard;
import pages.groupsPage.GroupsPage;
import pages.mainPage.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @AfterEach
    public void clean() {
        LeftNavigatePanel.goToGroupsPage();
        groupsPage.removeAllJoinedGroups();
    }
}
