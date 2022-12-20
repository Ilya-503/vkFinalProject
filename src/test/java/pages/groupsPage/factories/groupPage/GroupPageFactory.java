package pages.groupsPage.factories.groupPage;

import pages.groupPages.MyGroupPage;
import pages.groupPages.OtherGroupPage;


public class GroupPageFactory {
    public static GroupPage getGroupPage() {
        try {Thread.sleep(400);} catch (Exception e) { e.printStackTrace();} // локатор моей группы не прог. сразу
        return GroupPage.isMyGroup() ? new MyGroupPage() : new OtherGroupPage();
    }
}
