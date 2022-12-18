package pages.groupsPage.factories;

import pages.groupsPage.pageElements.EventCreationWindow;
import pages.groupsPage.pageElements.StandardGroupCreationWindow;

public class GroupCreationWindowFactory {
    public static GroupCreationWindow getGroupCreationWindow() {
        return GroupCreationWindow.isEventCrWn() ? new EventCreationWindow() : new StandardGroupCreationWindow();
    }
}
