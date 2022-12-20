package pages.groupsPage.factories.creationWindow;

import pages.groupsPage.factories.creationWindow.GroupCreationWindow;
import pages.groupsPage.pageElements.EventCreationWindow;
import pages.groupsPage.pageElements.StandardGroupCreationWindow;

public class GroupCreationWindowFactory {
    public static GroupCreationWindow getGroupCreationWindow() {
        return GroupCreationWindow.isEventCrWn() ? new EventCreationWindow() : new StandardGroupCreationWindow();
    }
}
