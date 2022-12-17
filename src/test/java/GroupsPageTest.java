import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.CurrentGroupPage;
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
        String title = firstCard.getTitle(), participAmount = firstCard.getParticipAmount();
        firstCard.joinGroup();
        CurrentGroupPage lastGroupPage = groupsPage.goToLastJoinedGroup();

        assertEquals(title, lastGroupPage.getGroupTitle(), "Название группы не совпало с ожидаемым");
        assertEquals(participAmount, lastGroupPage.getGroupMembersAmount(),
                "Кол-во участников группы не совпало с ожидаемым");
    }

    @AfterEach
    public void clean() {
        groupsPage.removeAllJoinedGroups();
    }
}
