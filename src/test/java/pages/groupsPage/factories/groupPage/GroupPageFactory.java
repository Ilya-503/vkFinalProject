package pages.groupsPage.factories.groupPage;

import pages.groupPages.MyGroupPage;
import pages.groupPages.OtherGroupPage;


public class GroupPageFactory {
    public static GroupPage getGroupPage() {

        try {
            Thread.sleep(600);
        } catch (Exception e) {

        }

            return GroupPage.isMyGroup() ? new MyGroupPage() : new OtherGroupPage();
    }
}
