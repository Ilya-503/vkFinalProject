import org.junit.jupiter.api.Test;
import pages.CurrentGroupPage;
import pages.groupsPage.GroupCard;
import pages.groupsPage.GroupsPage;
import pages.mainPage.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GroupsPageTest extends BaseTest {

    @Test
    public void testFunc() {
        logIn();
        GroupsPage groupsPage = new MainPage().goToGroupsPage();
        GroupCard firstCard = groupsPage.getFirstGroupCard();
        String title = firstCard.getTitle(), participAmount = firstCard.getParticipAmount();
        firstCard.joinGroup();
        CurrentGroupPage lastGroupPage = groupsPage.goToLastJoinedGroup();

       assertEquals(title, lastGroupPage.getGroupTitle());
       assertEquals(participAmount, lastGroupPage.getGroupMembersAmount());

    }
}
